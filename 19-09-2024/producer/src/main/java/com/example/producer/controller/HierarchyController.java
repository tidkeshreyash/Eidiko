package com.example.producer.controller;

import com.example.producer.service.HierarchyProducer;
import com.example.producer.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
public class HierarchyController {

    @Autowired
    private ProducerService producerService;

    @PostMapping("/send")
    public String sendStateCity(@RequestParam String state, @RequestParam String city) {
        producerService.sendStateCityData(state, city);
        return "Data sent to state: " + state + " and city: " + city;
    }
}