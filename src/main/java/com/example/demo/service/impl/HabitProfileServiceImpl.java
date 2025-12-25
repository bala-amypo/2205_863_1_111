package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.HabitProfile;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.HabitProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitProfileServiceImpl implements HabitProfileService {
    
    private final HabitProfileRepository habitRepo;

    public HabitProfileServiceImpl(HabitProfileRepository habitRepo) {
        this.habitRepo = habitRepo;
    }

    @Override
    public HabitProfile createOrUpdateHabit(HabitProfile habit) {
        if (habit.getStudyHoursPerDay() != null && habit.getStudyHoursPerDay() < 0) {
            throw new IllegalArgumentException("study hours must be >= 0");
        }
        
        Optional<HabitProfile> existing = habitRepo.findByStudentId(habit.getStudentId());
        if (existing.isPresent()) {
            HabitProfile existingHabit = existing.get();
            existingHabit.setStudyHoursPerDay(habit.getStudyHoursPerDay());
            existingHabit.setSleepSchedule(habit.getSleepSchedule());
            existingHabit.setCleanlinessLevelEnum(habit.getCleanlinessLevelEnum());
            existingHabit.setNoiseTolerance(habit.getNoiseTolerance());
            existingHabit.setSocialPreferenceEnum(habit.getSocialPreferenceEnum());
            return habitRepo.save(existingHabit);
        }
        
        return habitRepo.save(habit);
    }

    @Override
    public Optional<HabitProfile> getHabitById(Long id) {
        return habitRepo.findById(id);
    }

    @Override
    public HabitProfile getHabitByStudent(Long studentId) {
        return habitRepo.findByStudentId(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Habit profile not found"));
    }

    @Override
    public List<HabitProfile> getAllHabitProfiles() {
        return habitRepo.findAll();
    }
}