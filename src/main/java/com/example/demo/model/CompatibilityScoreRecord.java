package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "compatibility_scores")
public class CompatibilityScoreRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentAId;
    private Long studentBId;

    private Double score;

    @Enumerated(EnumType.STRING)
    private CompatibilityLevel compatibilityLevel;

    private LocalDateTime computedAt;

    @Column(columnDefinition = "TEXT")
    private String detailsJson;

    public enum CompatibilityLevel {
        POOR, MODERATE, GOOD, EXCELLENT
    }

    /* ================= GETTERS / SETTERS ================= */

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getStudentAId() { return studentAId; }
    public void setStudentAId(Long studentAId) { this.studentAId = studentAId; }

    public Long getStudentBId() { return studentBId; }
    public void setStudentBId(Long studentBId) { this.studentBId = studentBId; }

    public Double getScore() { return score; }
    public void setScore(Double score) {
        this.score = score;
        this.compatibilityLevel = deriveLevel(score);
    }

    public CompatibilityLevel getCompatibilityLevel() {
        return compatibilityLevel;
    }

    public void setCompatibilityLevel(CompatibilityLevel compatibilityLevel) {
        this.compatibilityLevel = compatibilityLevel;
    }

    public LocalDateTime getComputedAt() { return computedAt; }
    public void setComputedAt(LocalDateTime computedAt) {
        this.computedAt = computedAt;
    }

    public String getDetailsJson() { return detailsJson; }
    public void setDetailsJson(String detailsJson) {
        this.detailsJson = detailsJson;
    }

    private CompatibilityLevel deriveLevel(Double score) {
        if (score == null) return null;
        if (score >= 85) return CompatibilityLevel.EXCELLENT;
        if (score >= 65) return CompatibilityLevel.GOOD;
        if (score >= 40) return CompatibilityLevel.MODERATE;
        return CompatibilityLevel.POOR;
    } 
}
