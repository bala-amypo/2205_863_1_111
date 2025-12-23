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
public class CompatibilityScoreServiceImpl implements CompatibilityScoreService {}

private final CompatibilityScoreRecordRepository scoreRepository;
private final HabitProfileRepository habitRepository;

public CompatibilityScoreServiceImpl(
CompatibilityScoreRecordRepository scoreRepository,
HabitProfileRepository habitRepository) {
this.scoreRepository = scoreRepository;
this.habitRepository = habitRepository;
}

@Override
public CompatibilityScoreRecord computeScore(Long studentAId, Long studentBId) {

if (studentAId.equals(studentBId)) {
throw new IllegalArgumentException("same student");
}

HabitProfile a = habitRepository.findByStudentId(studentAId)
.orElseThrow(() -> new IllegalArgumentException("not found"));

HabitProfile b = habitRepository.findByStudentId(studentBId)
.orElseThrow(() -> new IllegalArgumentException("not found"));

double score = 0;

if (a.getSleepSchedule().equals(b.getSleepSchedule())) score += 25;
if (a.getCleanlinessLevel().equals(b.getCleanlinessLevel())) score += 25;
if (a.getNoiseTolerance().equals(b.getNoiseTolerance())) score += 25;
if (a.getSocialPreference().equals(b.getSocialPreference())) score += 25;

String level;
if (score < 40) level = "LOW";
else if (score < 70) level = "MEDIUM";
else if (score < 90) level = "HIGH";
else level = "EXCELLENT";

CompatibilityScoreRecord record = new CompatibilityScoreRecord();
record.setStudentAId(studentAId);
record.setStudentBId(studentBId);
record.setScore(score);
record.setCompatibilityLevel(level);
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