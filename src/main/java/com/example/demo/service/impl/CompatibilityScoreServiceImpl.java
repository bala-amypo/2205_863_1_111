package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.model.HabitProfile;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.CompatibilityScoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompatibilityScoreServiceImpl implements CompatibilityScoreService {
    
    private final CompatibilityScoreRecordRepository scoreRepo;
    private final HabitProfileRepository habitRepo;

    public CompatibilityScoreServiceImpl(CompatibilityScoreRecordRepository scoreRepo, HabitProfileRepository habitRepo) {
        this.scoreRepo = scoreRepo;
        this.habitRepo = habitRepo;
    }

    @Override
    public CompatibilityScoreRecord computeScore(Long studentAId, Long studentBId) {
        if (studentAId.equals(studentBId)) {
            throw new IllegalArgumentException("Cannot compute score for same student");
        }

        HabitProfile habitA = habitRepo.findByStudentId(studentAId)
                .orElseThrow(() -> new ResourceNotFoundException("Habit profile not found"));
        HabitProfile habitB = habitRepo.findByStudentId(studentBId)
                .orElseThrow(() -> new ResourceNotFoundException("Habit profile not found"));

        double score = calculateCompatibility(habitA, habitB);
        
        Optional<CompatibilityScoreRecord> existing = scoreRepo.findByStudentAIdAndStudentBId(studentAId, studentBId);
        CompatibilityScoreRecord record;
        
        if (existing.isPresent()) {
            record = existing.get();
            record.setScore(score);
        } else {
            record = new CompatibilityScoreRecord();
            record.setStudentAId(studentAId);
            record.setStudentBId(studentBId);
            record.setScore(score);
        }
        
        return scoreRepo.save(record);
    }

    private double calculateCompatibility(HabitProfile a, HabitProfile b) {
        double score = 100.0;
        
        if (a.getSleepSchedule() != null && b.getSleepSchedule() != null) {
            if (!a.getSleepSchedule().equals(b.getSleepSchedule())) {
                score -= 10;
            }
        }
        
        if (a.getCleanlinessLevelEnum() != null && b.getCleanlinessLevelEnum() != null) {
            if (!a.getCleanlinessLevelEnum().equals(b.getCleanlinessLevelEnum())) {
                score -= 15;
            }
        }
        
        if (a.getNoiseTolerance() != null && b.getNoiseTolerance() != null) {
            if (!a.getNoiseTolerance().equals(b.getNoiseTolerance())) {
                score -= 10;
            }
        }
        
        if (a.getSocialPreferenceEnum() != null && b.getSocialPreferenceEnum() != null) {
            if (!a.getSocialPreferenceEnum().equals(b.getSocialPreferenceEnum())) {
                score -= 5;
            }
        }
        
        return Math.max(0, Math.min(100, score));
    }

    @Override
    public CompatibilityScoreRecord getScoreById(Long id) {
        return scoreRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Score not found"));
    }

    @Override
    public List<CompatibilityScoreRecord> getScoresForStudent(Long studentId) {
        return scoreRepo.findByStudentAIdOrStudentBId(studentId, studentId);
    }

    @Override
    public List<CompatibilityScoreRecord> getAllScores() {
        return scoreRepo.findAll();
    }
}