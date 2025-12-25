package com.example.demo.controller;

import com.example.demo.model.StudentProfile;
import com.example.demo.service.StudentProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentProfileController {

    private final StudentProfileService service;

    public StudentProfileController(StudentProfileService service) {
        this.service = service;
    }

    // POST /api/students
    @PostMapping
    public StudentProfile createStudent(@RequestBody StudentProfile student) {
        return service.createStudent(student);
    }

    // GET /api/students/{id}
    @GetMapping("/{id}")
    public StudentProfile getById(@PathVariable Long id) {
        return service.getStudentById(id);
    }

    // GET /api/students
    @GetMapping
    public List<StudentProfile> getAll() {
        return service.getAllStudents();
    }

    // PUT /api/students/{id}/status?active=true
    @PutMapping("/{id}/status")
    public StudentProfile updateStatus(
            @PathVariable Long id,
            @RequestParam Boolean active) {
        return service.updateStudentStatus(id, active);
    }

    // GET /api/students/lookup/{studentId}
    @GetMapping("/lookup/{studentId}")
    public StudentProfile lookup(@PathVariable String studentId) {
        return service.findByStudentId(studentId).orElse(null);
    }
}
