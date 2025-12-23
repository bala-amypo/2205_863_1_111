package com.example.demo.controller;

import com.example.demo.service.CompatibilityScoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compatibility")
public class CompatibilityScoreController {}

private final CompatibilityScoreService service;

public CompatibilityScoreController(CompatibilityScoreService service) {
this.service = service;
}

@GetMapping("/{studentA}/{studentB}")
public int calculateCompatibility(
@PathVariable Long studentA,
@PathVariable Long studentB) {

return service.calculateScore(studentA, studentB);
}
}