package com.example.demo.controller;

import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.service.CompatibilityScoreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compatibility")
@Tag(name = "Compatibility Score")
public class CompatibilityScoreController {

private final CompatibilityScoreService service;

public CompatibilityScoreController(CompatibilityScoreService service) {
this.service = service;
}

@PostMapping("/calculate")
public ResponseEntity<CompatibilityScoreRecord> calculateScore(
@RequestParam Long studentAId,
@RequestParam Long studentBId) {
return ResponseEntity.ok(service.calculateScore(studentAId, studentBId));
}

@GetMapping("/{id}")
public ResponseEntity<CompatibilityScoreRecord> getById(@PathVariable Long id) {
return ResponseEntity.ok(service.getScoreById(id));
}

@GetMapping("/student/{studentId}")
public ResponseEntity<List<CompatibilityScoreRecord>> getScoresForStudent(
@PathVariable Long studentId) {
return ResponseEntity.ok(service.getScoresForStudent(studentId));
}

@GetMapping
public ResponseEntity<List<CompatibilityScoreRecord>> getAllScores() {
return ResponseEntity.ok(service.getAllScores());
}
}