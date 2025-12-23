package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "match_attempt_record")
public class MatchAttemptRecord {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private Long studentAId;

private Long studentBId;

private String result;

private LocalDateTime attemptedAt;

public MatchAttemptRecord() {
this.attemptedAt = LocalDateTime.now();
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

public String getResult() {
return result;
}

public void setResult(String result) {
this.result = result;
}

public LocalDateTime getAttemptedAt() {
return attemptedAt;
}

public void setAttemptedAt(LocalDateTime attemptedAt) {
this.attemptedAt = attemptedAt;
}
}