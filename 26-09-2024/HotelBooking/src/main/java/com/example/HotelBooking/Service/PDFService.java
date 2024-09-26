package com.example.HotelBooking.Service;

import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Service
public class PDFService {

    @Autowired
    private OCRService ocrService;

    public String extractTextFromPDF(MultipartFile file) throws IOException {
        PDDocument document = PDDocument.load(file.getInputStream());
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);
        document.close();
        return text;
    }

    public List<String> extractTextFromPDFImages(MultipartFile file, OCRService ocrService) throws IOException, TesseractException {
        PDDocument document = PDDocument.load(file.getInputStream());
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        List<String> ocrResults = new ArrayList<>();

        for (int page = 0; page < document.getNumberOfPages(); page++) {
            BufferedImage image = pdfRenderer.renderImageWithDPI(page, 300); // 300 DPI for better quality
            MultipartFile imageFile = createMultipartFileFromImage(image, "page-" + page + ".jpg");
            String ocrText = ocrService.extractTextFromImage(imageFile); // Use OCR service to extract text from image
            ocrResults.add(ocrText);
        }

        document.close();
        return ocrResults;
    }
    public MultipartFile createMultipartFileFromImage(BufferedImage image, String fileName) throws IOException {
        // Convert BufferedImage to byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        byte[] imageBytes = baos.toByteArray();

        // Create and return MultipartFile
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
                return "image/jpeg";  // Adjust to your image type, e.g., "image/png"
            }

            @Override
            public boolean isEmpty() {
                return imageBytes == null || imageBytes.length == 0;
            }

            @Override
            public long getSize() {
                return imageBytes.length;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return imageBytes;
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return new ByteArrayInputStream(imageBytes);
            }

            @Override
            public void transferTo(File dest) throws IOException {
                Files.write(dest.toPath(), imageBytes);
            }
        };
    }

}
