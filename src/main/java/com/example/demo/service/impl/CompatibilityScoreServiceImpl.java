package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.service.CompatibilityScoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CompatibilityScoreServiceImpl implements CompatibilityScoreService {

private final CompatibilityScoreRecordRepository repository;

public CompatibilityScoreServiceImpl(CompatibilityScoreRecordRepository repository) {
this.repository = repository;
}

@Override
public CompatibilityScoreRecord calculateScore(Long student1Id, Long student2Id) {

if (student1Id.equals(student2Id)) {
throw new IllegalArgumentException("same student");
}

CompatibilityScoreRecord record = new CompatibilityScoreRecord();
record.setStudent1Id(student1Id);
record.setStudent2Id(student2Id);
record.setScore(new Random().nextInt(41) + 60); 

return repository.save(record);
}

@Override
public CompatibilityScoreRecord getScoreById(Long id) {
return repository.findById(id)
.orElseThrow(() -> new ResourceNotFoundException("not found"));
}

@Override
public List<CompatibilityScoreRecord> getScoresForStudent(Long studentId) {
return repository.findByStudent1Id(studentId);
}


@Override
public List<CompatibilityScoreRecord> getAllScores() {
return compatibilityScoreRecordRepository.findAll();
}
}