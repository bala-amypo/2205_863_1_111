package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.HabitProfile;
import com.example.demo.model.MatchResult;
import com.example.demo.model.StudentProfile;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.repository.MatchResultRepository;
import com.example.demo.repository.StudentProfileRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class MatchService {

    private final MatchResultRepository matchResultRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final HabitProfileRepository habitProfileRepository;

    public MatchService(MatchResultRepository matchResultRepository,
                        StudentProfileRepository studentProfileRepository,
                        HabitProfileRepository habitProfileRepository) {
        this.matchResultRepository = matchResultRepository;
        this.studentProfileRepository = studentProfileRepository;
        this.habitProfileRepository = habitProfileRepository;
    }

    public MatchResult computeMatch(Long studentAId, Long studentBId) {

        StudentProfile studentA = studentProfileRepository.findById(studentAId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        StudentProfile studentB = studentProfileRepository.findById(studentBId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        HabitProfile habitA = habitProfileRepository.findByStudentId(studentAId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        HabitProfile habitB = habitProfileRepository.findByStudentId(studentBId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        double score = calculateCompatibilityScore(habitA, habitB);

        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0-100");
        }

        String reasonSummary = generateReasonSummary(habitA, habitB, score);

        MatchResult result = new MatchResult(studentA, studentB, score, reasonSummary);
        return matchResultRepository.save(result);
    }

    public List<MatchResult> getMatchesFor(Long studentId) {
        return matchResultRepository
                .findByStudentAIdOrStudentBIdOrderByScoreDesc(studentId, studentId);
    }

    public MatchResult getById(Long id) {
        return matchResultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match not found"));
    }

    private double calculateCompatibilityScore(HabitProfile habitA, HabitProfile habitB) {

        double score = 100.0;

        // Sleep time compatibility
        if (habitA.getSleepTime() != null && habitB.getSleepTime() != null) {
            score -= calculateTimeDifferencePenalty(
                    habitA.getSleepTime(), habitB.getSleepTime()) * 0.2;
        }

        // Wake time compatibility
        if (habitA.getWakeTime() != null && habitB.getWakeTime() != null) {
            score -= calculateTimeDifferencePenalty(
                    habitA.getWakeTime(), habitB.getWakeTime()) * 0.2;
        }

        // Smoking compatibility
        if (habitA.getSmoking() != null && habitB.getSmoking() != null &&
                !habitA.getSmoking().equals(habitB.getSmoking())) {
            score -= 15;
        }

        // Drinking compatibility
        if (habitA.getDrinking() != null && habitB.getDrinking() != null &&
                !habitA.getDrinking().equals(habitB.getDrinking())) {
            score -= 10;
        }

        // Cleanliness compatibility (ENUM → ordinal)
        if (habitA.getCleanlinessLevel() != null && habitB.getCleanlinessLevel() != null) {
            score -= Math.abs(
                    habitA.getCleanlinessLevel().ordinal()
                            - habitB.getCleanlinessLevel().ordinal()
            ) * 5;
        }

        // Noise tolerance compatibility (ENUM → ordinal)
        if (habitA.getNoiseTolerance() != null && habitB.getNoiseTolerance() != null) {
            score -= Math.abs(
                    habitA.getNoiseTolerance().ordinal()
                            - habitB.getNoiseTolerance().ordinal()
            ) * 5;
        }

        return Math.max(0, Math.min(100, score));
    }

    private double calculateTimeDifferencePenalty(LocalTime time1, LocalTime time2) {
        int diff = Math.abs(time1.getHour() - time2.getHour());
        return Math.min(diff, 24 - diff) * 2;
    }

    private String generateReasonSummary(HabitProfile habitA, HabitProfile habitB, double score) {
        if (score >= 80) return "Excellent compatibility";
        if (score >= 60) return "Good compatibility";
        if (score >= 40) return "Moderate compatibility";
        return "Low compatibility";
    }
}
