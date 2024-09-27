package com.example.SendMailThroughExcel.controller;


import com.example.SendMailThroughExcel.entity.JwtUser;
import com.example.SendMailThroughExcel.service.JwtUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class JwtUserController {

    @Autowired
    private JwtUserService jwtUserService;


    @PostMapping("/create")
    public ResponseEntity<JwtUser> createUser(@RequestBody JwtUser jwtUser){
        jwtUserService.createUser(jwtUser);
        return new ResponseEntity<>(jwtUser, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<JwtUser>> getAllUsers(){
        return new ResponseEntity<>(jwtUserService.allUsers(),HttpStatus.OK);
    }

    @PostMapping("/login")
    public String login(@RequestBody JwtUser user){
        return jwtUserService.verify(user);
    }

}
