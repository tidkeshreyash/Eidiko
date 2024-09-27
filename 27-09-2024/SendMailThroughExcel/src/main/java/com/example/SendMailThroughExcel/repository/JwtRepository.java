package com.example.SendMailThroughExcel.repository;

import com.example.SendMailThroughExcel.entity.JwtUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtRepository extends JpaRepository<JwtUser,Long> {
    JwtUser findByUsername(String username);
}
