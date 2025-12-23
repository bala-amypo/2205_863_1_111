// com/example/demo/service/HabitProfileService.java
package com.example.demo.service;

import com.example.demo.model.HabitProfile;
import java.util.List;

public interface HabitProfileService {
HabitProfile createOrUpdateHabit(HabitProfile habit);
HabitProfile getHabitByStudent(Long studentId);
HabitProfile getHabitById(Long id);
List<HabitProfile> getAllHabitProfiles();
}