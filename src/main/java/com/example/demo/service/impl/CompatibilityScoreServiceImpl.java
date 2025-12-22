package com.example.demo.service.impl;

import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.model.HabitProfile;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.CompatibilityScoreService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompatibilityScoreServiceImpl implements CompatibilityScoreService {

private final CompatibilityScoreRecordRepository scoreRepository;
private final HabitProfileRepository habitRepository;

public CompatibilityScoreServiceImpl(
CompatibilityScoreRecordRepository scoreRepository,
HabitProfileRepository habitRepository
) {
this.scoreRepository = scoreRepository;
this.habitRepository = habitRepository;
}

@Override
public CompatibilityScoreRecord calculateScore(Long studentAId, Long studentBId) {

if (studentAId.equals(studentBId)) {
throw new IllegalArgumentException("same student");
}

HabitProfile a = habitRepository.findByStudentId(studentAId)
.orElseThrow(() -> new IllegalArgumentException("not found"));

HabitProfile b = habitRepository.findByStudentId(studentBId)
.orElseThrow(() -> new IllegalArgumentException("not found"));

CompatibilityScoreRecord record = new CompatibilityScoreRecord();
record.setStudentAId(studentAId);
record.setStudentBId(studentBId);
record.setScore(50.0); 
record.setCompatibilityLevel("MEDIUM");
record.setComputedAt(LocalDateTime.now());
record.setDetailsJson("{}");

return scoreRepository.save(record);
}

@Override
public CompatibilityScoreRecord getScoreById(Long id) {
return scoreRepository.findById(id)
.orElseThrow(() -> new IllegalArgumentException("not found"));
}

@Override
public List<CompatibilityScoreRecord> getScoresForStudent(Long studentId) {
return scoreRepository.findByStudentAIdOrStudentBId(studentId, studentId);
}

@Override
public List<CompatibilityScoreRecord> getAllScores() {
return scoreRepository.findAll();
}
}