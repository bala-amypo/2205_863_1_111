package com.example.demo.controller;

import com.example.demo.model.HabitProfile;
import com.example.demo.service.HabitProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
@Tag(name = "Habit Profile")
public class HabitProfileController {

private final HabitProfileService habitProfileService;

public HabitProfileController(HabitProfileService habitProfileService) {
this.habitProfileService = habitProfileService;
}

@PostMapping
public HabitProfile createOrUpdate(@RequestBody HabitProfile habit) {
return habitProfileService.createOrUpdateHabit(habit);
}

@GetMapping("/student/{studentId}")
public HabitProfile getByStudent(@PathVariable Long studentId) {
return habitProfileService.getHabitByStudent(studentId);
}

@GetMapping("/{id}")
public HabitProfile getById(@PathVariable Long id) {
return habitProfileService.getHabitById(id);
}

@GetMapping
public List<HabitProfile> getAll() {
return habitProfileService.getAllHabitProfiles();
}
}