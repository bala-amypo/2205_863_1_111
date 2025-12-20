package com.example.demo.controller;

import com.example.demo.model.CompatibilityScore;
import com.example.demo.service.CompatibilityScoreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compatibility")
@Tag(name = "Compatibility Score")
public class CompatibilityScoreController {

private final CompatibilityScoreService compatibilityScoreService;

public CompatibilityScoreController(CompatibilityScoreService compatibilityScoreService) {
this.compatibilityScoreService = compatibilityScoreService;
}

@PostMapping("/calculate")
public CompatibilityScore calculate(
@RequestParam Long student1Id,
@RequestParam Long student2Id) {

return compatibilityScoreService.calculateScore(student1Id, student2Id);
}

@GetMapping("/{id}")
public CompatibilityScore getById(@PathVariable Long id) {
return compatibilityScoreService.getScoreById(id);
}

@GetMapping("/student/{studentId}")
public List<CompatibilityScore> getForStudent(@PathVariable Long studentId) {
return compatibilityScoreService.getScoresForStudent(studentId);
}
}