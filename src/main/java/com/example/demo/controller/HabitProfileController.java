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
    public HabitProfile createOrUpdate(@RequestBody HabitProfile profile) {
        return service.saveHabit(profile);
    }

    @GetMapping("/student/{studentId}")
    public HabitProfile getByStudent(@PathVariable Long studentId) {
        return service.getByStudentId(studentId);
    }

    @GetMapping("/{id}")
    public HabitProfile getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<HabitProfile> getAll() {
        return service.getAllHabits();
    }
}
