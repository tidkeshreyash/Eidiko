package com.example.rbMQ.controller;

import com.example.rbMQ.service.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageProducer messageProducer;

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody String jsonMessage) {
        messageProducer.sendMessage(jsonMessage);
        return ResponseEntity.ok("Message sent to RabbitMQ");
    }
}
