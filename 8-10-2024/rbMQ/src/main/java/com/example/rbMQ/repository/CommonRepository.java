package com.example.rbMQ.repository;

import com.example.rbMQ.entity.CommonMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommonRepository extends JpaRepository<CommonMessage,Long> {
    List<CommonMessage> findByIsRead(boolean isRead);
}
