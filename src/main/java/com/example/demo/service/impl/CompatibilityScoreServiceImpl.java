// com/example/demo/service/impl/CompatibilityScoreServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.model.HabitProfile;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.CompatibilityScoreService;

import java.util.List;

public class CompatibilityScoreServiceImpl implements CompatibilityScoreService {

private final CompatibilityScoreRecordRepository scoreRepo;
private final HabitProfileRepository habitRepo;

public CompatibilityScoreServiceImpl(
CompatibilityScoreRecordRepository scoreRepo,
HabitProfileRepository habitRepo) {
this.scoreRepo = scoreRepo;
this.habitRepo = habitRepo;
}

@Override
public CompatibilityScoreRecord computeScore(Long studentAId, Long studentBId) {
if (studentAId.equals(studentBId)) {
throw new IllegalArgumentException("same student");
}

HabitProfile a = habitRepo.findByStudentId(studentAId)
.orElseThrow(() -> new ResourceNotFoundException("not found"));
HabitProfile b = habitRepo.findByStudentId(studentBId)
.orElseThrow(() -> new ResourceNotFoundException("not found"));

double score = 50.0;
String level = "MEDIUM";

CompatibilityScoreRecord record = new CompatibilityScoreRecord();
record.setStudentAId(studentAId);
record.setStudentBId(studentBId);
record.setScore(score);
record.setCompatibilityLevel(level);
record.setDetailsJson("{}");

return scoreRepo.save(record);
}

@Override
public CompatibilityScoreRecord getScoreById(Long id) {
return scoreRepo.findById(id)
.orElseThrow(() -> new ResourceNotFoundException("not found"));
}

@Override
public List<CompatibilityScoreRecord> getScoresForStudent(Long studentId) {
return scoreRepo.findByStudentAIdOrStudentBId(studentId, studentId).stream().toList();
}
}