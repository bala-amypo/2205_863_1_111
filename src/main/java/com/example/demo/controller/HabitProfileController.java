package com.example.demo.controller;

import com.example.demo.model.HabitProfile;
import com.example.demo.service.HabitProfileService;
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
    public HabitProfile saveOne(@RequestBody HabitProfile habit) {
        return service.createOrUpdateHabit(habit);
    }

    @PostMapping("/bulk")
    public List<HabitProfile> saveAll(@RequestBody List<HabitProfile> habits) {
        return habits.stream()
                .map(service::createOrUpdateHabit)
                .toList();
    }

    @GetMapping("/{id}")
    public HabitProfile getById(@PathVariable Long id) {
        return service.getHabitById(id);
    }

    @GetMapping("/student/{studentId}")
    public HabitProfile getByStudent(@PathVariable Long studentId) {
        return service.getHabitByStudent(studentId);
    }

    @GetMapping
    public List<HabitProfile> getAll() {
        return service.getAllHabitProfiles();
    }
}
