package com.example.demo.controller;

import com.example.demo.model.StudentProfile;
import com.example.demo.service.StudentProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentProfileController {
    
    private final StudentProfileService studentService;

    public StudentProfileController(StudentProfileService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentProfile> create(@RequestBody StudentProfile student) {
        StudentProfile created = studentService.createStudent(student);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentProfile> getById(@PathVariable Long id) {
        StudentProfile student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }
}