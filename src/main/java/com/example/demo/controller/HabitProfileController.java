package com.example.demo.controller;

import com.example.demo.model.HabitProfile;
import com.example.demo.service.HabitProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/habits")
@Tag(name = "Habit Profile")
public class HabitProfileController {

private final HabitProfileService service;

public HabitProfileController(HabitProfileService service) {
this.service = service;
}

@PostMapping
public ResponseEntity<HabitProfile> createHabit(
@RequestBody HabitProfile habitProfile) {
return ResponseEntity.ok(service.saveHabit(habitProfile));
}

@GetMapping("/{studentId}")
public ResponseEntity<HabitProfile> getByStudentId(
@PathVariable Long studentId) {
return ResponseEntity.ok(service.getByStudentId(studentId));
}
}