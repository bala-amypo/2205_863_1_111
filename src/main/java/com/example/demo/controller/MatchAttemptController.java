package com.example.demo.controller;

import com.example.demo.model.MatchAttemptRecord;
import com.example.demo.service.MatchAttemptService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match-attempts")
@Tag(name = "Match Attempts")
public class MatchAttemptController {

private final MatchAttemptService service;

public MatchAttemptController(MatchAttemptService service) {
this.service = service;
}

@PostMapping
public ResponseEntity<MatchAttemptRecord> attemptMatch(
@RequestParam Long studentAId,
@RequestParam Long studentBId) {
return ResponseEntity.ok(service.recordAttempt(studentAId, studentBId));
}

@GetMapping("/student/{studentId}")
public ResponseEntity<List<MatchAttemptRecord>> getAttemptsForStudent(
@PathVariable Long studentId) {
return ResponseEntity.ok(service.getAttemptsForStudent(studentId));
}
}