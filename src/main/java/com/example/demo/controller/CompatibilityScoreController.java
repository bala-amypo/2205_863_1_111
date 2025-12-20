package com.example.demo.controller;

import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.service.CompatibilityScoreService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
public CompatibilityScoreRecord calculate(
@RequestParam Long student1Id,
@RequestParam Long student2Id) {

return service.calculateScore(student1Id, student2Id);
}

@GetMapping("/{id}")
public CompatibilityScoreRecord getById(@PathVariable Long id) {
return service.getScoreById(id);
}

@GetMapping("/student/{studentId}")
public List<CompatibilityScoreRecord> getForStudent(
@PathVariable Long studentId) {

return service.getScoresForStudent(studentId);
}
@GetMapping
public List<CompatibilityScoreRecord> getAll() {
return service.getAllScores();
}
}