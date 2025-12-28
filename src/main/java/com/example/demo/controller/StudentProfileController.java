package com.example.demo.controller;

import com.example.demo.model.StudentProfile;
import com.example.demo.service.StudentProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<StudentProfile>> getAll() {
        List<StudentProfile> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<StudentProfile> updateStatus(@PathVariable Long id, @RequestParam Boolean active) {
        StudentProfile updated = studentService.updateStudentStatus(id, active);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/lookup/{studentId}")
    public ResponseEntity<StudentProfile> lookupByStudentId(@PathVariable String studentId) {
        return studentService.findByStudentId(studentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}