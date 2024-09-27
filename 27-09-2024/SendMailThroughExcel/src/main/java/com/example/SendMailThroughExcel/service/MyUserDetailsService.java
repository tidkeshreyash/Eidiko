package com.example.SendMailThroughExcel.service;

import com.example.SendMailThroughExcel.entity.JwtUser;
import com.example.SendMailThroughExcel.entity.JwtUserPrincipal;
import com.example.SendMailThroughExcel.repository.JwtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipal;

@Service
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    private JwtRepository jwtRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JwtUser user = jwtRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User Not Found");
        }

        return new JwtUserPrincipal(user);

    }
}
