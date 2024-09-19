package com.example.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class HierarchyProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendStateCityData(String state, String city) {
        // Create a hierarchical topic name using state and city
        String topic = "india." + state.toLowerCase() + "." + city.toLowerCase();

        // Send a message to the Kafka topic
        kafkaTemplate.send(topic, "Data for state: " + state + ", city: " + city);
       // System.out.println("Sent to " + topic + ": Data for state: " + state + ", city: " + city);
    }
}
