package com.example.rbMQ.repository;

import com.example.rbMQ.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long>{
}
