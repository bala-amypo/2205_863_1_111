package com.example.demo.controller;

import com.example.demo.service.CompatibilityScoreService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compatibility")
class CompatibilityScoreController {

private final CompatibilityScoreService service;

CompatibilityScoreController(CompatibilityScoreService service) {
this.service = service;
}

@PostMapping
int calculate(@RequestParam Long studentAId,
@RequestParam Long studentBId) {
return service.calculateScore(studentAId, studentBId);
}
}