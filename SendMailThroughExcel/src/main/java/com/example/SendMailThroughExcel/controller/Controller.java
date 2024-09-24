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
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/email")
public class Controller {

    @Autowired
    private FileService fileService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmails(
            @RequestParam("from") String from,
            @RequestParam("cc") String cc,
            @RequestParam("subject") String subject,
            @RequestParam("body") String body,
            @RequestParam("excelFile") MultipartFile excelFile,
            @RequestParam("zipFile") MultipartFile zipFile) {

        try {
            // Extract user data from Excel
            List<User> users = fileService.extractUsersFromExcel(excelFile);

            // Extract files from zip
            Map<String, File> extractedFiles = fileService.extractFilesFromZip(zipFile);

            // Send emails with file attachments
            for (User user : users) {
                File fileToAttach = extractedFiles.get(user.getFileName());

                if (fileToAttach != null) {
                    emailService.sendEmail(from, cc, user.getEmail(), subject, body, fileToAttach);
                } else {
                    // Handle missing file case (optional)
                    System.out.println("File not found for user: " + user.getEmail());
                }
            }

            return ResponseEntity.ok("Emails sent successfully");

        } catch (IOException | MessagingException e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}

