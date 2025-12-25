package com.example.demo.controller;

import com.example.demo.model.MatchAttemptRecord;
import com.example.demo.service.MatchAttemptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match-attempts")
public class MatchAttemptController {
    
    private final MatchAttemptService attemptService;

    public MatchAttemptController(MatchAttemptService attemptService) {
        this.attemptService = attemptService;
    }

    @PostMapping
    public ResponseEntity<MatchAttemptRecord> log(@RequestBody MatchAttemptRecord attempt) {
        MatchAttemptRecord logged = attemptService.logMatchAttempt(attempt);
        return ResponseEntity.ok(logged);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<MatchAttemptRecord> updateStatus(@PathVariable Long id, @RequestParam String status) {
        MatchAttemptRecord updated = attemptService.updateAttemptStatus(id, status);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<MatchAttemptRecord>> getByStudent(@PathVariable Long studentId) {
        List<MatchAttemptRecord> attempts = attemptService.getAttemptsByStudent(studentId);
        return ResponseEntity.ok(attempts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchAttemptRecord> getById(@PathVariable Long id) {
        MatchAttemptRecord attempt = attemptService.getAttemptById(id);
        return ResponseEntity.ok(attempt);
    }

    @GetMapping
    public ResponseEntity<List<MatchAttemptRecord>> getAll() {
        List<MatchAttemptRecord> attempts = attemptService.getAllMatchAttempts();
        return ResponseEntity.ok(attempts);
    }
}