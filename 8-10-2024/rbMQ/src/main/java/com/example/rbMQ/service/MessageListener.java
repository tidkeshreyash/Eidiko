package com.example.rbMQ.service;


import com.example.rbMQ.config.RabbitConfig;
import com.example.rbMQ.entity.CommonMessage;
import com.example.rbMQ.repository.CommonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {
    @Autowired
    private CommonRepository commonMessageRepository;


    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void receiveMessage(byte[] messageBytes) throws JsonProcessingException {
        //(messageBytes,Charset_UTF8)
        String message = new String(messageBytes);

        // Save to common table
        CommonMessage commonMessage = new CommonMessage();
        commonMessage.setMessage(message);
        commonMessage.setIsRead(true); // Mark as true immediately after saving
        commonMessageRepository.save(commonMessage);
    }
}
