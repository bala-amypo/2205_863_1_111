package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.model.MatchAttemptRecord;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.repository.MatchAttemptRecordRepository;
import com.example.demo.service.MatchAttemptService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchAttemptServiceImpl implements MatchAttemptService {
    
    private final MatchAttemptRecordRepository matchRepo;
    private final CompatibilityScoreRecordRepository scoreRepo;

    public MatchAttemptServiceImpl(MatchAttemptRecordRepository matchRepo, CompatibilityScoreRecordRepository scoreRepo) {
        this.matchRepo = matchRepo;
        this.scoreRepo = scoreRepo;
    }

    @Override
    public MatchAttemptRecord logMatchAttempt(MatchAttemptRecord attempt) {
        if (attempt.getResultScoreId() != null) {
            CompatibilityScoreRecord score = scoreRepo.findById(attempt.getResultScoreId()).orElse(null);
            if (score != null) {
                attempt.setStatus(MatchAttemptRecord.Status.MATCHED);
            }
        } else {
            attempt.setStatus(MatchAttemptRecord.Status.PENDING_REVIEW);
        }
        
        return matchRepo.save(attempt);
    }

    @Override
    public MatchAttemptRecord updateAttemptStatus(Long id, String status) {
        MatchAttemptRecord attempt = matchRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match attempt not found"));
        
        attempt.setStatus(MatchAttemptRecord.Status.valueOf(status));
        return matchRepo.save(attempt);
    }

    @Override
    public List<MatchAttemptRecord> getAttemptsByStudent(Long studentId) {
        return matchRepo.findByInitiatorStudentIdOrCandidateStudentId(studentId, studentId);
    }

    @Override
    public List<MatchAttemptRecord> getAllMatchAttempts() {
        return matchRepo.findAll();
    }
}