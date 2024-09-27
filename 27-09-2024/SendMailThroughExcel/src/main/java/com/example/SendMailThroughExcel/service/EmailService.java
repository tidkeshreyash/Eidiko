package com.example.SendMailThroughExcel.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String from, String cc, String to, String subject, String body, List<File> attachments) throws MessagingException, MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(from);
        helper.setTo(to);
        helper.setCc(cc);
        helper.setSubject(subject);
        helper.setText(body, true);

        // Add attachment


        for (File file : attachments) {
            helper.addAttachment(file.getName(), file); // Attach files
        }
        mailSender.send(message);
    }
}
