package com.example.rbMQ.service;

import com.example.rbMQ.entity.CommonMessage;
import com.example.rbMQ.entity.Department;
import com.example.rbMQ.entity.Student;
import com.example.rbMQ.repository.CommonRepository;
import com.example.rbMQ.repository.DepartmentRepo;
import com.example.rbMQ.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageScheduler {

    @Autowired
    private CommonRepository commonMessageRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentRepo departmentRepository;

    @Scheduled(fixedRate = 120000) // 120000 milliseconds = 2 minutes
    public void processMessages() {
        // Fetch messages where isRead is true
        List<CommonMessage> messages = commonMessageRepository.findByIsRead(true);

        for (CommonMessage commonMessage : messages) {
            try {
                // Deserialize JSON
                String message = commonMessage.getMessage();
                JSONObject json = new JSONObject(message);
                ObjectMapper objectMapper = new ObjectMapper();

                // Process student if exists
                if (json.has("student")) {
                    Student student = objectMapper.readValue(json.getJSONObject("student").toString(), Student.class);
                    studentRepository.save(student);
                }

                // Process department if exists
                if (json.has("department")) {
                    Department department = objectMapper.readValue(json.getJSONObject("department").toString(), Department.class);
                    departmentRepository.save(department);
                }

                // Set isRead to false after processing
                commonMessage.setIsRead(false);
                commonMessageRepository.save(commonMessage);

            } catch (Exception e) {
                e.printStackTrace(); // Handle exception as needed
            }
        }
    }
}
