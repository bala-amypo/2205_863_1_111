package com.example.demo.controller;

import com.example.demo.model.StudentProfile;
import com.example.demo.service.StudentProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Profile")
public class StudentProfileController {

private final StudentProfileService service;

public StudentProfileController(StudentProfileService service) {
this.service = service;
}

@PostMapping
public StudentProfile create(@RequestBody StudentProfile profile) {
return service.createStudent(profile);
}

@GetMapping("/{id}")
public StudentProfile get(@PathVariable Long id) {
return service.getStudentById(id);
}

@GetMapping
public List<StudentProfile> list() {
return service.getAllStudents();
}
}