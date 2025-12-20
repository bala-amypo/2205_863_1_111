package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class HabitProfile {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private Long studentId;
private Integer studyHoursPerDay;
private LocalDateTime updatedAt = LocalDateTime.now();

public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
}

public Long getStudentId() {
return studentId;
}

public void setStudentId(Long studentId) {
this.studentId = studentId;
}

public Integer getStudyHoursPerDay() {
return studyHoursPerDay;
}

public void setStudyHoursPerDay(Integer studyHoursPerDay) {
this.studyHoursPerDay = studyHoursPerDay;
}

public LocalDateTime getUpdatedAt() {
return updatedAt;
}
}