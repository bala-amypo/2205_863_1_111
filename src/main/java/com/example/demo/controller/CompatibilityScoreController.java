package com.example.demo.controller;

import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.service.CompatibilityScoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compatibility")
public class CompatibilityScoreController {

    private final CompatibilityScoreService service;

    public CompatibilityScoreController(CompatibilityScoreService service) {
        this.service = service;
    }

    // POST /compute/{studentAId}/{studentBId}
    @PostMapping("/compute/{studentAId}/{studentBId}")
    public CompatibilityScoreRecord compute(
            @PathVariable Long studentAId,
            @PathVariable Long studentBId) {
        return service.computeScore(studentAId, studentBId);
    }

    // GET /student/{studentId}
    @GetMapping("/student/{studentId}")
    public List<CompatibilityScoreRecord> getByStudent(@PathVariable Long studentId) {
        return service.getScoresForStudent(studentId);
    }

    // GET /{id}
    @GetMapping("/{id}")
    public CompatibilityScoreRecord getById(@PathVariable Long id) {
        return service.getScoreById(id);
    }

    // GET /
    @GetMapping
    public List<CompatibilityScoreRecord> getAll() {
        return service.getAllScores();
    }
}
