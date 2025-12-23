package com.example.demo.service.impl;

import com.example.demo.model.MatchAttemptRecord;
import com.example.demo.repository.MatchAttemptRecordRepository;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.service.MatchAttemptService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MatchAttemptServiceImpl implements MatchAttemptService {

private final MatchAttemptRecordRepository repo;
private final CompatibilityScoreRecordRepository scoreRepo;

public MatchAttemptServiceImpl(
MatchAttemptRecordRepository repo,
CompatibilityScoreRecordRepository scoreRepo) {
this.repo = repo;
this.scoreRepo = scoreRepo;
}

@Override
public MatchAttemptRecord logMatchAttempt(MatchAttemptRecord attempt) {
attempt.setAttemptedAt(LocalDateTime.now());
return repo.save(attempt);
}

@Override
public MatchAttemptRecord updateAttemptStatus(Long id, String status) {
MatchAttemptRecord record = getAttemptById(id);
record.setStatus(status);
return repo.save(record);
}

@Override
public MatchAttemptRecord getAttemptById(Long id) {
return repo.findById(id)
.orElseThrow(() -> new IllegalArgumentException("not found"));
}

@Override
public List<MatchAttemptRecord> getAttemptsByStudent(Long studentId) {
return repo.findByInitiatorStudentIdOrCandidateStudentId(studentId, studentId);
}

@Override
public List<MatchAttemptRecord> getAllMatchAttempts() {
return repo.findAll();
}
}