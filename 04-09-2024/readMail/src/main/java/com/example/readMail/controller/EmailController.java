package com.example.readMail.controller;

import com.example.readMail.service.GmailReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emails")
public class EmailController {

    @Autowired
    private GmailReader gmailReader;

    @GetMapping("/fetch")
    public String fetchEmails() {
        try {
            return gmailReader.fetchEmails();
        } catch (Exception e) {
            return "Error fetching emails: " + e.getMessage();
        }
    }
}