package com.example.demo.controller;

import com.example.demo.model.StudentProfile;
import com.example.demo.service.StudentProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Profile")
public class StudentProfileController {

private final StudentProfileService studentProfileService;

public StudentProfileController(StudentProfileService studentProfileService) {
this.studentProfileService = studentProfileService;
}

@PostMapping
public ResponseEntity<StudentProfile> createStudent(
@RequestBody StudentProfile studentProfile) {

return ResponseEntity.ok(
studentProfileService.createStudent(studentProfile)
);
}

@GetMapping("/{id}")
public ResponseEntity<StudentProfile> getStudentById(
@PathVariable Long id) {

return ResponseEntity.ok(
studentProfileService.getStudentById(id)
);
}

@GetMapping
public ResponseEntity<List<StudentProfile>> getAllStudents() {

return ResponseEntity.ok(
studentProfileService.getAllStudents()
);
}

@PutMapping("/{id}/status")
public ResponseEntity<StudentProfile> updateStudentStatus(
@PathVariable Long id,
@RequestParam boolean active) {

return ResponseEntity.ok(
studentProfileService.updateStudentStatus(id, active)
);
}

@GetMapping("/lookup/{studentId}")
public ResponseEntity<StudentProfile> lookupByStudentId(
@PathVariable String studentId) {

return ResponseEntity.ok(
studentProfileService.findByStudentId(studentId)
);
}

@PostMapping("/students")
public ResponseEntity<StudentProfile> createStudent(
        @Valid @RequestBody StudentProfile studentProfile) {

    return ResponseEntity.ok(studentProfileService.save(studentProfile));
}

}