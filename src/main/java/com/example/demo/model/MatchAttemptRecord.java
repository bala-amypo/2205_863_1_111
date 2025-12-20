package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MatchAttemptRecord {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private Long initiatorStudentId;
private Long candidateStudentId;
private String status;
private LocalDateTime attemptedAt = LocalDateTime.now();

public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
}

public Long getInitiatorStudentId() {
return initiatorStudentId;
}

public void setInitiatorStudentId(Long initiatorStudentId) {
this.initiatorStudentId = initiatorStudentId;
}

public Long getCandidateStudentId() {
return candidateStudentId;
}

public void setCandidateStudentId(Long candidateStudentId) {
this.candidateStudentId = candidateStudentId;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public LocalDateTime getAttemptedAt() {
return attemptedAt;
}
}