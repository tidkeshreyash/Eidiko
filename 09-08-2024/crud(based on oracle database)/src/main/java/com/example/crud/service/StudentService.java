package com.example.crud.service;

import com.example.crud.dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto createStudent(StudentDto dto);

    StudentDto getStudentById(Long studentId);

    List<StudentDto> getAllStudents();

    StudentDto updateStudent(Long studentId, StudentDto updatedCustomer);

    void deleteStudent(Long studentId);
}
