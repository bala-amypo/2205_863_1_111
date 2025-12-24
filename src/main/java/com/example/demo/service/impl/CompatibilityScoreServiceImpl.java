package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.model.HabitProfile;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.CompatibilityScoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompatibilityScoreServiceImpl implements CompatibilityScoreService {

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
                .orElseThrow(() -> new ResourceNotFoundException("habit not found"));
        HabitProfile b = habitRepository.findByStudentId(studentBId)
                .orElseThrow(() -> new ResourceNotFoundException("habit not found"));

        int score = 0;
        int total = 0;

        if (a.getSleepSchedule() != null && b.getSleepSchedule() != null) {
            total += 20;
            if (a.getSleepSchedule().equalsIgnoreCase(b.getSleepSchedule())) {
                score += 20;
            }
        }

        if (a.getCleanlinessLevel() != null && b.getCleanlinessLevel() != null) {
            total += 20;
            if (a.getCleanlinessLevel().equalsIgnoreCase(b.getCleanlinessLevel())) {
                score += 20;
            }
        }

        if (a.getNoiseTolerance() != null && b.getNoiseTolerance() != null) {
            total += 20;
            if (a.getNoiseTolerance().equalsIgnoreCase(b.getNoiseTolerance())) {
                score += 20;
            }
        }

        if (a.getSocialPreference() != null && b.getSocialPreference() != null) {
            total += 20;
            if (a.getSocialPreference().equalsIgnoreCase(b.getSocialPreference())) {
                score += 20;
            }
        }

        if (a.getStudyHoursPerDay() != null && b.getStudyHoursPerDay() != null) {
            total += 20;
            int diff = Math.abs(a.getStudyHoursPerDay() - b.getStudyHoursPerDay());
            if (diff <= 1) {
                score += 20;
            } else if (diff <= 3) {
                score += 10;
            }
        }

        double finalScore = total == 0 ? 0 : (score * 100.0) / total;

        String level;
        if (finalScore >= 80) {
            level = "HIGH";
        } else if (finalScore >= 50) {
            level = "MEDIUM";
        } else {
            level = "LOW";
        }

        CompatibilityScoreRecord record = new CompatibilityScoreRecord();
        record.setStudentAId(studentAId);
        record.setStudentBId(studentBId);
        record.setScore(finalScore);
        record.setCompatibilityLevel(level);
        record.setDetailsJson("{\"matchedScore\":" + score + ",\"totalScore\":" + total + "}");

        return scoreRepository.save(record);
    }

    @Override
    public CompatibilityScoreRecord getScoreById(Long id) {
        return scoreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
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
