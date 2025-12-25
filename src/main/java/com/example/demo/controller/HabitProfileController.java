package com.example.demo.controller;

import com.example.demo.model.HabitProfile;
import com.example.demo.service.HabitProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/habits")
public class HabitProfileController {
    
    private final HabitProfileService habitService;

    public HabitProfileController(HabitProfileService habitService) {
        this.habitService = habitService;
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<HabitProfile> getByStudent(@PathVariable Long studentId) {
        HabitProfile habit = habitService.getHabitByStudent(studentId);
        return ResponseEntity.ok(habit);
    }

    @PostMapping
    public ResponseEntity<HabitProfile> create(@RequestBody HabitProfile habit) {
        HabitProfile created = habitService.createOrUpdateHabit(habit);
        return ResponseEntity.ok(created);
    }
}