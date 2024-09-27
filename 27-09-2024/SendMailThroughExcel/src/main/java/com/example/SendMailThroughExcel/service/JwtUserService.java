package com.example.SendMailThroughExcel.service;


import com.example.SendMailThroughExcel.entity.JwtUser;
import com.example.SendMailThroughExcel.repository.JwtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Service
public class JwtUserService {


    @Autowired
    private JwtRepository jwtRepository;
    
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);



    public JwtUser createUser(JwtUser user){
        user.setPassword(encoder.encode(user.getPassword()));
        return jwtRepository.save(user);
    }

    public List<JwtUser> allUsers(){
        return jwtRepository.findAll();
    }

    public String verify(JwtUser user) {
        try {
            Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            if (authentication.isAuthenticated()) {
                return jwtService.generateToken(user.getUsername());
            } else {
                return "fail";
            }
        } catch (Exception e) {
            e.printStackTrace(); 
            return "Authentication failed: " + e.getMessage();
        }
    }


}
