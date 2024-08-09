package com.example.crud.service.impl;

import com.example.crud.dto.StudentDto;
import com.example.crud.entity.Student;
import com.example.crud.exception.ResourceNotFoundException;
import com.example.crud.mapper.StudentMapper;
import com.example.crud.repository.StudentRepository;
import com.example.crud.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ServiceImpl implements StudentService {

    private StudentRepository studentRepository;


    @Override
    public StudentDto createStudent(StudentDto dto) {
        Student student = StudentMapper.maptoStudent(dto);
        Student savedStudent = studentRepository.save(student);
        return StudentMapper.maptoDto(savedStudent);
    }



    @Override
    public StudentDto getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student is not present"));

        return StudentMapper.maptoDto(student);
    }

    public List<StudentDto> getAllStudents(){
        List<Student> students = studentRepository.findAll();
        return students.stream().map((student) -> StudentMapper.maptoDto(student))
                .collect(Collectors.toList());

    }

    @Override
    public StudentDto updateStudent(Long studentId, StudentDto updatedCustomer) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("student is not present")
                );
        student.setName(updatedCustomer.getName());
        student.setCollegeName(updatedCustomer.getCollegeName());

        Student updatedStudentObj = studentRepository.save(student);
        return StudentMapper.maptoDto(updatedStudentObj);
    }

    @Override
    public void deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student is not present"));
        studentRepository.deleteById(studentId);
    }
}
