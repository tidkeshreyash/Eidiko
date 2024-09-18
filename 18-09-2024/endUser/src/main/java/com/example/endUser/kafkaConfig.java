package com.example.endUser;


import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.sql.SQLOutput;

@Configuration
public class kafkaConfig {



    @KafkaListener(topics = AppConstants.LOCATION_UPDATE_TOPIC,groupId = AppConstants.GROUP_ID)
    public void updatedLocation(String value){
        System.out.println(value);
    }

}
