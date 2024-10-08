package com.example.rbMQ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RbMqApplication {

	public static void main(String[] args) {
		SpringApplication.run(RbMqApplication.class, args);
	}

}
