// com/example/demo/service/impl/HabitProfileServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.HabitProfile;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.HabitProfileService;

import java.util.List;

public class HabitProfileServiceImpl implements HabitProfileService {

private final HabitProfileRepository habitProfileRepository;

public HabitProfileServiceImpl(HabitProfileRepository habitProfileRepository) {
this.habitProfileRepository = habitProfileRepository;
}

@Override
public HabitProfile createOrUpdateHabit(HabitProfile habit) {
if (habit.getStudyHoursPerDay() < 0) {
throw new IllegalArgumentException("study hours");
}
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