package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "compatibility_score")
public class CompatibilityScore {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private Long student1Id;
private Long student2Id;

private Integer score;   // 0 – 100

private LocalDateTime calculatedAt;

public CompatibilityScore() {
this.calculatedAt = LocalDateTime.now();
}

public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
}

public Long getStudent1Id() {
return student1Id;
}

public void setStudent1Id(Long student1Id) {
this.student1Id = student1Id;
}

public Long getStudent2Id() {
return student2Id;
}

public void setStudent2Id(Long student2Id) {
this.student2Id = student2Id;
}

public Integer getScore() {
return score;
}

public void setScore(Integer score) {
this.score = score;
}

public LocalDateTime getCalculatedAt() {
return calculatedAt;
}

public void setCalculatedAt(LocalDateTime calculatedAt) {
this.calculatedAt = calculatedAt;
}
}