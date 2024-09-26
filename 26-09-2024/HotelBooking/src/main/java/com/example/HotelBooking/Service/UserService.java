package com.example.HotelBooking.Service;


import com.example.HotelBooking.Entity.User;
import com.example.HotelBooking.Repository.UserRepository;
import com.example.HotelBooking.exception.ResourceNotFoundException;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private OCRService ocrService;

    @Autowired
    private PDFService pdfService;

    @Autowired
    private JwtService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User createUser(String name, String email, String phoneNumber, String address, String city, String panNumber,String password, MultipartFile aadhaarCardPhoto) throws IOException {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);
        user.setCity(city);
        user.setPanNumber(panNumber);
        user.setPassword(encoder.encode(password));

        // Convert image to base64
        String base64Image = Base64.getEncoder().encodeToString(aadhaarCardPhoto.getBytes());
        user.setAadhaarCardPhoto(base64Image);

        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPhoneNumber(userDetails.getPhoneNumber());
        user.setAddress(userDetails.getAddress());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public String getAadhaarCardData(Long id) throws IOException, TesseractException {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        String aadhaarData = null;

        if (user.getAadhaarCardPhoto() != null) {
            // Decode the Base64 encoded string to bytes
            byte[] photoBytes = Base64.getDecoder().decode(user.getAadhaarCardPhoto());

            if (isPDF(photoBytes)) {
                // Create a MultipartFile for the PDF
                MultipartFile pdfFile = createMultipartFile(photoBytes, "aadhaar.pdf");
                aadhaarData = pdfService.extractTextFromPDF(pdfFile);
                if (aadhaarData.trim().isEmpty()) {
                    List<String> ocrResults = pdfService.extractTextFromPDFImages(pdfFile, ocrService);
                    aadhaarData = String.join("\n", ocrResults);
                }
            } else {
                // Create a MultipartFile for the image
                MultipartFile imageFile = createMultipartFile(photoBytes, "aadhaar.jpg");
                aadhaarData = ocrService.extractTextFromImage(imageFile);
            }
        }

        return aadhaarData;
    }

    private boolean isPDF(byte[] fileBytes) {
        // Simple check for PDF by its magic number
        return fileBytes.length > 4 && fileBytes[0] == 0x25 && fileBytes[1] == 0x50 &&
                fileBytes[2] == 0x44 && fileBytes[3] == 0x46; // %PDF
    }

    private MultipartFile createMultipartFile(byte[] fileBytes, String fileName) {
        return new MultipartFile() {
            @Override
            public String getName() {
                return fileName;
            }

            @Override
            public String getOriginalFilename() {
                return fileName;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return fileBytes == null || fileBytes.length == 0;
            }

            @Override
            public long getSize() {
                return fileBytes.length;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return fileBytes;
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return new ByteArrayInputStream(fileBytes);
            }



            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {
                Files.write(dest.toPath(), fileBytes);
            }
        };
    }

    @Autowired
    AuthenticationManager authenticationManager;

    public String verify(User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(),user.getPassword()));

        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getName());


        return "fail";
    }
}
