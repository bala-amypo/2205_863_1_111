package com.example.demo.controller;

import com.example.demo.model.HabitProfile;
import com.example.demo.service.HabitProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
public class HabitProfileController {
    
    private final HabitProfileService habitService;

    public HabitProfileController(HabitProfileService habitService) {
        this.habitService = habitService;
    }

    @PostMapping
    public ResponseEntity<HabitProfile> create(@RequestBody HabitProfile habit) {
        HabitProfile created = habitService.createOrUpdateHabit(habit);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<HabitProfile> getByStudent(@PathVariable Long studentId) {
        HabitProfile habit = habitService.getHabitByStudent(studentId);
        return ResponseEntity.ok(habit);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitProfile> getById(@PathVariable Long id) {
        return habitService.getHabitById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<HabitProfile>> getAll() {
        List<HabitProfile> habits = habitService.getAllHabitProfiles();
        return ResponseEntity.ok(habits);
    }
}