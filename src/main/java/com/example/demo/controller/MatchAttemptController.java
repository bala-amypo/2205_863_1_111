package com.example.demo.controller;

import com.example.demo.model.MatchAttemptRecord;
import com.example.demo.service.MatchAttemptService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match-attempts")
public class MatchAttemptController {

    private final MatchAttemptService service;

    public MatchAttemptController(MatchAttemptService service) {
        this.service = service;
    }

    // POST /api/match-attempts
    @PostMapping
    public MatchAttemptRecord log(@RequestBody MatchAttemptRecord record) {
        return service.logMatchAttempt(record);
    }

    // PUT /api/match-attempts/{id}/status
    @PutMapping("/{id}/status")
    public MatchAttemptRecord updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return service.updateAttemptStatus(id, status);
    }

    // GET /api/match-attempts/student/{studentId}
    @GetMapping("/student/{studentId}")
    public List<MatchAttemptRecord> getByStudent(
            @PathVariable Long studentId) {
        return service.getAttemptsByStudent(studentId);
    }

    // GET /api/match-attempts
    @GetMapping
    public List<MatchAttemptRecord> getAll() {
        return service.getAllMatchAttempts();
    }
}
