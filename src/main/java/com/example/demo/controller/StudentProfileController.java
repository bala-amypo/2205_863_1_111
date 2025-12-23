package com.example.demo.controller;

import com.example.demo.model.StudentProfile;
import com.example.demo.service.StudentProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Profile")
public class StudentProfileController {}

private final StudentProfileService service;

public StudentProfileController(StudentProfileService service) {
this.service = service;
}

@PostMapping
public ResponseEntity<StudentProfile> createStudent(
@Valid @RequestBody StudentProfile studentProfile) {
return ResponseEntity.ok(service.createStudent(studentProfile));
}

@GetMapping("/{id}")
public ResponseEntity<StudentProfile> getStudentById(@PathVariable Long id) {
return ResponseEntity.ok(service.getStudentById(id));
}

@GetMapping
public ResponseEntity<List<StudentProfile>> getAllStudents() {
return ResponseEntity.ok(service.getAllStudents());
}

@PutMapping("/{id}/status")
public ResponseEntity<StudentProfile> updateStudentStatus(
@PathVariable Long id,
@RequestParam boolean active) {
return ResponseEntity.ok(service.updateStudentStatus(id, active));
}

@GetMapping("/lookup/{studentId}")
public ResponseEntity<StudentProfile> getByStudentId(
@PathVariable String studentId) {
return ResponseEntity.ok(service.findByStudentId(studentId));
}
}