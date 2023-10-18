package com.example.testcontainers.controller;

import com.example.testcontainers.entity.Student;
import com.example.testcontainers.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/add_student")
    public Student addStudent(Student student){
        return studentService.addStudents(student);
    }

    @RequestMapping("/students")
    public List<Student> getAllStudents()
    {
        return studentService.getAllStudents();
    }

}
