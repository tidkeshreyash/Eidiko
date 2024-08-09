package com.example.crud.controller;

import com.example.crud.dto.StudentDto;
import com.example.crud.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/clg/student")
public class StudentController {
   private StudentService studentService;

   @PostMapping
   public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto dto){
      StudentDto savedStudent = studentService.createStudent(dto);
      return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
   }

   @GetMapping("{studentId}")
   public ResponseEntity<StudentDto> getStudentById(@PathVariable("studentId") Long studentId){
      StudentDto dto = studentService.getStudentById(studentId);
      return ResponseEntity.ok(dto);
   }

   @GetMapping
   public ResponseEntity<List<StudentDto>> getAllStudents(){
      List<StudentDto> students = studentService.getAllStudents();
      return ResponseEntity.ok(students);
   }

   @PutMapping("{studentId}")
   public ResponseEntity<StudentDto> updateStudent(@PathVariable("studentId") Long studentId, @RequestBody StudentDto updatedStudent){
      StudentDto studentDt = studentService.updateStudent(studentId,updatedStudent);
      return ResponseEntity.ok(studentDt);
   }

   @DeleteMapping("{studentId}")
   public ResponseEntity<String> deleteStudent(@PathVariable("studentId") Long studentId){
      studentService.deleteStudent(studentId);
      return ResponseEntity.ok("Student deleted successfully");
   }

}
