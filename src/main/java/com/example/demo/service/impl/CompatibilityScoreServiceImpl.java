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
            throw new IllegalArgumentException("Same student not allowed");
        }

        HabitProfile a = habitRepository.findByStudentId(studentAId)
                .orElseThrow(() -> new IllegalArgumentException("Student A habit not found"));

        HabitProfile b = habitRepository.findByStudentId(studentBId)
                .orElseThrow(() -> new IllegalArgumentException("Student B habit not found"));

        int score = 0;

        if (a.getSleepSchedule().equalsIgnoreCase(b.getSleepSchedule())) {
            score += 40;
        }

        if (a.getCleanlinessLevel().equalsIgnoreCase(b.getCleanlinessLevel())) {
            score += 30;
        }

        if (a.getStudyPreference().equalsIgnoreCase(b.getStudyPreference())) {
            score += 30;
        }

        String level;
        if (score >= 70) {
            level = "HIGH";
        } else if (score >= 40) {
            level = "MEDIUM";
        } else {
            level = "LOW";
        }

        CompatibilityScoreRecord record = new CompatibilityScoreRecord();
        record.setStudentAId(studentAId);
        record.setStudentBId(studentBId);
        record.setScore((double) score); // FIXED
        record.setCompatibilityLevel(level);
        record.setComputedAt(LocalDateTime.now());
        record.setDetailsJson("{\"scoreLogic\":\"string match\"}");

        return scoreRepository.save(record);
    }

    @Override
    public CompatibilityScoreRecord getScoreById(Long id) {
        return scoreRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Score not found"));
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
