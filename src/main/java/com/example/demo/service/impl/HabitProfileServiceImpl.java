package com.example.demo.service.impl;

import com.example.demo.model.HabitProfile;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.HabitProfileService;
import org.springframework.stereotype.Service;

@Service
public class HabitProfileServiceImpl implements HabitProfileService {

private final HabitProfileRepository repository;

public HabitProfileServiceImpl(HabitProfileRepository repository) {
this.repository = repository;
}

@Override
public HabitProfile save(HabitProfile habit) {
return repository.save(habit);
}

@Override
public HabitProfile findByStudentId(Long studentId) {
return repository.findByStudentId(studentId)
.orElseThrow(() -> new IllegalArgumentException("Habit profile not found"));
}
}