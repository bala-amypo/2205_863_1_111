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

    public CompatibilityScoreServiceImpl(
            CompatibilityScoreRecordRepository scoreRepo,
            HabitProfileRepository habitRepo) {
        this.scoreRepo = scoreRepo;
        this.habitRepo = habitRepo;
    }

    @Override
    public CompatibilityScoreRecord computeScore(Long studentAId, Long studentBId) {

        if (studentAId.equals(studentBId)) {
            throw new IllegalArgumentException("Cannot compute score for same student");
        }

        HabitProfile a = habitRepo.findByStudentId(studentAId)
                .orElseThrow(() -> new ResourceNotFoundException("Habit profile not found"));

        HabitProfile b = habitRepo.findByStudentId(studentBId)
                .orElseThrow(() -> new ResourceNotFoundException("Habit profile not found"));

        double score = calculateCompatibility(a, b);

        Optional<CompatibilityScoreRecord> existing =
                scoreRepo.findByStudentAIdAndStudentBId(studentAId, studentBId);

        CompatibilityScoreRecord record = existing.orElseGet(CompatibilityScoreRecord::new);
        record.setStudentAId(studentAId);
        record.setStudentBId(studentBId);
        record.setScore(score);

        return scoreRepo.save(record);
    }

    private double calculateCompatibility(HabitProfile a, HabitProfile b) {

        double score = 100.0;

        // Sleep schedule
        if (a.getSleepSchedule() != null && b.getSleepSchedule() != null &&
                a.getSleepSchedule() != b.getSleepSchedule()) {
            score -= 10;
        }

        // Cleanliness
        if (a.getCleanlinessLevel() != null && b.getCleanlinessLevel() != null) {
            score -= Math.abs(
                    a.getCleanlinessLevel().ordinal()
                            - b.getCleanlinessLevel().ordinal()
            ) * 5;
        }

        // Noise tolerance
        if (a.getNoiseTolerance() != null && b.getNoiseTolerance() != null) {
            score -= Math.abs(
                    a.getNoiseTolerance().ordinal()
                            - b.getNoiseTolerance().ordinal()
            ) * 3;
        }

        // Social preference
        if (a.getSocialPreference() != null && b.getSocialPreference() != null &&
                a.getSocialPreference() != b.getSocialPreference()) {
            score -= 5;
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
