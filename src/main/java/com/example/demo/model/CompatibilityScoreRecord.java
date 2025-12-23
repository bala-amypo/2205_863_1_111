package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "compatibility_score_record")
public class CompatibilityScoreRecord {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private Long studentAId;

private Long studentBId;

private Double score;

private String compatibilityLevel;

private LocalDateTime computedAt;

@Lob
private String detailsJson;

public CompatibilityScoreRecord() {
}

public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
}

public Long getStudentAId() {
return studentAId;
}

public void setStudentAId(Long studentAId) {
this.studentAId = studentAId;
}

public Long getStudentBId() {
return studentBId;
}

public void setStudentBId(Long studentBId) {
this.studentBId = studentBId;
}

public Double getScore() {
return score;
}

public void setScore(Double score) {
this.score = score;
}

public String getCompatibilityLevel() {
return compatibilityLevel;
}

public void setCompatibilityLevel(String compatibilityLevel) {
this.compatibilityLevel = compatibilityLevel;
}

public LocalDateTime getComputedAt() {
return computedAt;
}

public void setComputedAt(LocalDateTime computedAt) {
this.computedAt = computedAt;
}

public String getDetailsJson() {
return detailsJson;
}

public void setDetailsJson(String detailsJson) {
this.detailsJson = detailsJson;
}
}