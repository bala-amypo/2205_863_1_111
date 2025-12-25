package com.example.demo.controller;

import com.example.demo.model.HabitProfile;
import com.example.demo.service.HabitProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
public class HabitProfileController {

    private final HabitProfileService service;

    public HabitProfileController(HabitProfileService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<HabitProfile> createOrUpdate(@RequestBody HabitProfile habit) {
        return ResponseEntity.ok(service.createOrUpdateHabit(habit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitProfile> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getHabitById(id).orElse(null));
    }

    @GetMapping
    public ResponseEntity<List<HabitProfile>> getAll() {
        return ResponseEntity.ok(service.getAllHabitProfiles());
    }
}
