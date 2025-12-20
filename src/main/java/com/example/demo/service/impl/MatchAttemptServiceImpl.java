package com.example.demo.service.impl;

import com.example.demo.model.MatchAttemptRecord;
import com.example.demo.repository.MatchAttemptRecordRepository;
import com.example.demo.service.MatchAttemptService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MatchAttemptServiceImpl implements MatchAttemptService {

private final MatchAttemptRecordRepository repository;

public MatchAttemptServiceImpl(MatchAttemptRecordRepository repository) {
this.repository = repository;
}

@Override
public MatchAttemptRecord logAttempt(MatchAttemptRecord record) {
record.setAttemptedAt(LocalDateTime.now());
return repository.save(record);
}

@Override
public MatchAttemptRecord updateStatus(Long id, String status) {
MatchAttemptRecord record = repository.findById(id)
.orElseThrow(() -> new RuntimeException("Attempt not found"));
record.setStatus(status);
return repository.save(record);
}

@Override
public MatchAttemptRecord getById(Long id) {
return repository.findById(id)
.orElseThrow(() -> new RuntimeException("Attempt not found"));
}

@Override
public List<MatchAttemptRecord> getByStudent(Long studentId) {
return repository.findByStudentAIdOrStudentBId(studentId, studentId);
}

@Override
public List<MatchAttemptRecord> getAll() {
return repository.findAll();
}
}