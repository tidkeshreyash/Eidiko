package com.example.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendStateCityData(String state, String city) {
        String topic = "india." + state.toLowerCase() + "." + city.toLowerCase();
        kafkaTemplate.send(topic, "Data for state: " + state + ", city: " + city);
        System.out.println("Sent to " + topic + ": Data for state: " + state + ", city: " + city);
    }
}
