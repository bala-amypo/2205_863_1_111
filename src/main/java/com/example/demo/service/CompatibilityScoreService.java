package com.example.demo.service;

import com.example.demo.model.CompatibilityScoreRecord;

import java.util.List;

public interface CompatibilityScoreService {

CompatibilityScoreRecord calculateScore(Long student1Id, Long student2Id);

CompatibilityScoreRecord getScoreById(Long id);

List<CompatibilityScoreRecord> getScoresForStudent(Long studentId);
}