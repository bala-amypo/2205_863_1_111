package com.example.demo.controller;

import com.example.demo.model.CompatibilityScore;
import com.example.demo.service.CompatibilityScoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compatibility")
public class MatchController {

    private final CompatibilityScoreService service;

    public MatchController(CompatibilityScoreService service) {
        this.service = service;
    }

    @PostMapping("/compute/{studentAId}/{studentBId}")
    public CompatibilityScore compute(
            @PathVariable Long studentAId,
            @PathVariable Long studentBId) {
        return service.computeScore(studentAId, studentBId);
    }

    @GetMapping("/student/{studentId}")
    public List<CompatibilityScore> getByStudent(@PathVariable Long studentId) {
        return service.getScoresByStudent(studentId);
    }

    @GetMapping("/{id}")
    public CompatibilityScore getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<CompatibilityScore> getAll() {
        return service.getAllScores();
    }
}
