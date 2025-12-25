package com.example.demo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "match_attempt_records")
public class MatchAttemptRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long initiatorStudentId;
    private Long candidateStudentId;
    private Long resultScoreId;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING_REVIEW;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public enum Status {
        PENDING_REVIEW, MATCHED, REJECTED
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getInitiatorStudentId() { return initiatorStudentId; }
    public void setInitiatorStudentId(Long initiatorStudentId) { this.initiatorStudentId = initiatorStudentId; }

    public Long getCandidateStudentId() { return candidateStudentId; }
    public void setCandidateStudentId(Long candidateStudentId) { this.candidateStudentId = candidateStudentId; }

    public Long getResultScoreId() { return resultScoreId; }
    public void setResultScoreId(Long resultScoreId) { this.resultScoreId = resultScoreId; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}