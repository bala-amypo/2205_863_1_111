package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.HabitProfile;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.HabitProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitProfileServiceImpl implements HabitProfileService {

    private final HabitProfileRepository repository;

    public HabitProfileServiceImpl(HabitProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public HabitProfile createOrUpdateHabit(HabitProfile habit) {
        if (habit.getStudyHoursPerDay() != null && habit.getStudyHoursPerDay() < 0) {
            throw new IllegalArgumentException("invalid study hours");
        }
        return repository.save(habit);
    }

    @Override
    public HabitProfile getHabitByStudent(Long studentId) {
        return repository.findByStudentId(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("habit not found"));
    }

    @Override
    public HabitProfile getHabitById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("habit not found"));
    }

    @Override
    public List<HabitProfile> getAllHabitProfiles() {
        return repository.findAll();
    }
}
