package com.example.demo.service;

import com.example.demo.model.CompatibilityScore;

import java.util.List;

public interface CompatibilityScoreService {

CompatibilityScore calculateScore(Long student1Id, Long student2Id);

CompatibilityScore getScoreById(Long id);

List<CompatibilityScore> getScoresForStudent(Long studentId);
}