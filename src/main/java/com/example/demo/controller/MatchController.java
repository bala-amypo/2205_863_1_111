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

@PostMapping
public MatchAttemptRecord logAttempt(@RequestBody MatchAttemptRecord record) {
return service.logAttempt(record);
}

@PutMapping("/{id}/status")
public MatchAttemptRecord updateStatus(
@PathVariable Long id,
@RequestParam String status
) {
return service.updateStatus(id, status);
}

@GetMapping("/{id}")
public MatchAttemptRecord getById(@PathVariable Long id) {
return service.getById(id);
}

@GetMapping("/student/{studentId}")
public List<MatchAttemptRecord> getByStudent(@PathVariable Long studentId) {
return service.getByStudent(studentId);
}

@GetMapping
public List<MatchAttemptRecord> getAll() {
return service.getAll();
}
}