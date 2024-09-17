package com.example.userService.service;

import com.example.userService.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface UserService {

    User saveUser(User user) throws JsonProcessingException;
    List<User> getAllUser();
    User getUser(String userId) throws JsonProcessingException;
}
