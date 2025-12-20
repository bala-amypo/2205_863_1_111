package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.HabitProfile;
import com.example.demo.repository.HabitProfileRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HabitProfileServiceImpl implements HabitProfileService {

private final HabitProfileRepository habitProfileRepository;

// Constructor Injection (MANDATORY)
public HabitProfileServiceImpl(HabitProfileRepository habitProfileRepository) {
this.habitProfileRepository = habitProfileRepository;
}

@Override
public HabitProfile createOrUpdateHabit(HabitProfile habit) {

if (habit.getStudyHoursPerDay() != null && habit.getStudyHoursPerDay() < 0) {
throw new IllegalArgumentException("study hours");
}

habit.setUpdatedAt(LocalDateTime.now());
return habitProfileRepository.save(habit);
}

@Override
public HabitProfile getHabitByStudent(Long studentId) {
return habitProfileRepository.findByStudentId(studentId)
.orElseThrow(() -> new ResourceNotFoundException("not found"));
}

@Override
public HabitProfile getHabitById(Long id) {
return habitProfileRepository.findById(id)
.orElseThrow(() -> new ResourceNotFoundException("not found"));
}

@Override
public List<HabitProfile> getAllHabitProfiles() {
return habitProfileRepository.findAll();
}
}