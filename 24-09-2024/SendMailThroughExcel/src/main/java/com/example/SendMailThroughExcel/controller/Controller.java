package com.example.SendMailThroughExcel.controller;


import com.example.SendMailThroughExcel.entity.User;
import com.example.SendMailThroughExcel.service.EmailService;
import com.example.SendMailThroughExcel.service.FileService;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/email")
public class Controller {

    @Autowired
    private FileService fileService;

    @Autowired
    private EmailService emailService;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmails(
            @RequestParam("from") String from,
            @RequestParam("cc") String cc,
            @RequestParam("subject") String subject,
            @RequestParam("body") String body,
            @RequestParam("excelFile") MultipartFile excelFile,
            @RequestParam("zipFile") MultipartFile zipFile) {

        List<String> failedEmails = new ArrayList<>();
        List<String> invalidEmails = new ArrayList<>();
        List<String> missingFilesEmails = new ArrayList<>();

        try {
            // Step 1: Extract user data from Excel
            List<User> users = fileService.extractUsersFromExcel(excelFile);

            // Step 2: Extract files from the zip file
            Map<String, File> extractedFiles = fileService.extractFilesFromZip(zipFile);

            // Step 3: Process each user and send emails with the corresponding file(s)
            for (User user : users) {
                // Check if the email is valid
                if (!isValidEmail(user.getEmail())) {
                    invalidEmails.add(user.getEmail());
                    continue; // Skip invalid email addresses
                }

                // Collect files to attach for this user
                List<File> filesToAttach = new ArrayList<>();
                for (String fileName : user.getFileNames()) {
                    File fileToAttach = extractedFiles.get(fileName.trim());
                    if (fileToAttach != null) {
                        filesToAttach.add(fileToAttach);
                    }
                }

                // If no files found, add to missing files list and skip email sending
                if (filesToAttach.isEmpty()) {
                    missingFilesEmails.add(user.getEmail());
                    continue; // Skip users with missing files
                }

                // Personalize the body of the email
                String personalizedBody = "Dear " + user.getName() + ",<br><br>" + body.replaceAll("\n", "<br>");

                try {
                    // Send the email with multiple attachments
                    emailService.sendEmail(from, cc, user.getEmail(), subject, personalizedBody, filesToAttach);
                } catch (MessagingException e) {
                    // Add failed email to the list
                    failedEmails.add(user.getEmail());
                    System.out.println("Failed to send email to: " + user.getEmail() + " - " + e.getMessage());
                }
            }

            // Create the response message
            StringBuilder responseMessage = new StringBuilder("Emails sent successfully");
            if (!invalidEmails.isEmpty()) {
                responseMessage.append(", but invalid emails were found: ").append(String.join(", ", invalidEmails));
            }
            if (!failedEmails.isEmpty()) {
                responseMessage.append(", but failed for: ").append(String.join(", ", failedEmails));
            }
            if (!missingFilesEmails.isEmpty()) {
                responseMessage.append(", but files were missing for: ").append(String.join(", ", missingFilesEmails));
            }

            return ResponseEntity.ok(responseMessage.toString());

        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}