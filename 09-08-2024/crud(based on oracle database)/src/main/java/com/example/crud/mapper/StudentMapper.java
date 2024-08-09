package com.example.crud.mapper;

import com.example.crud.dto.StudentDto;
import com.example.crud.entity.Student;

public class StudentMapper {

    public static StudentDto maptoDto(Student student){
        return new StudentDto(
                student.getId(),
                student.getName(),
                student.getCollegeName()
        );
    }

    public static Student maptoStudent(StudentDto dto){
        return new Student(
                dto.getId(),
                dto.getName(),
                dto.getCollegeName()
        );
    }
}
