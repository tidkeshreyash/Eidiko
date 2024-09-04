package com.example.readMail.service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;


@Service
public class GmailReader {

    private static final String HOST = "imap.gmail.com";
    private static final String USERNAME = "shreyashtidke.eidiko@gmail.com";
    private static final String PASSWORD = "ybqs ltpj jllf cyzq";
    private static final String KEYWORD = "Code";

    public String fetchEmails() throws Exception {
        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imaps.host", HOST);
        properties.put("mail.imaps.port", "993");

        Session session = Session.getInstance(properties);
        Store store = session.getStore("imaps");
        store.connect(HOST, USERNAME, PASSWORD);

        Folder inbox = store.getFolder("inbox");
        inbox.open(Folder.READ_ONLY);

        Message[] messages = inbox.getMessages();
        StringBuilder emailContent = new StringBuilder();

        if (messages != null && messages.length > 0) {
            for (Message message : messages) {
                if (message.getSubject() != null && message.getSubject().contains(KEYWORD)) {
                    Address[] ccAddresses = message.getRecipients(Message.RecipientType.CC);
                    boolean isCc = false;

                    if (ccAddresses != null) {
                        for (Address address : ccAddresses) {
                            if (address.toString().equalsIgnoreCase(USERNAME)) {
                                isCc = true;
                                break;
                            }
                        }
                    }

                    if (isCc) {
                        emailContent.append("Subject: ").append(message.getSubject()).append("\n");
                        emailContent.append("From: ").append(message.getFrom()[0]).append("\n");
                        emailContent.append("Date: ").append(message.getSentDate()).append("\n");
                        emailContent.append("Content: ").append(getTextFromMessage(message)).append("\n");
                        emailContent.append("---------------------------------\n");
                    }
                }
            }
        }

        inbox.close(false);
        store.close();

        return emailContent.toString();
    }

    private String getTextFromMessage(Message message) throws Exception {
        Object content = message.getContent();
        StringBuilder text = new StringBuilder();

        if (content instanceof String) {
            text.append(content);
        } else if (content instanceof MimeMultipart) {
            MimeMultipart multipart = (MimeMultipart) content;
            int count = multipart.getCount();
            for (int i = 0; i < count; i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                if (bodyPart.isMimeType("text/plain")) {
                    text.append(bodyPart.getContent().toString());
                } else if (bodyPart.isMimeType("text/html")) {
                    text.append("HTML content is not displayed.");
                }
            }
        } else {
            text.append("Unknown content type.");
        }

        return text.toString();
    }
}

