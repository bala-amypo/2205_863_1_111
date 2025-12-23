package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "habit_profiles")
public class HabitProfile {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private Long studentId;
private String sleepSchedule;
private Integer studyHoursPerDay;
private String cleanlinessLevel;
private String noiseTolerance;
private String socialPreference;
private LocalDateTime updatedAt = LocalDateTime.now();

public Long getId() { return id; }
public Long getStudentId() { return studentId; }
public void setStudentId(Long studentId) { this.studentId = studentId; }
public String getSleepSchedule() { return sleepSchedule; }
public void setSleepSchedule(String sleepSchedule) { this.sleepSchedule = sleepSchedule; }
public Integer getStudyHoursPerDay() { return studyHoursPerDay; }
public void setStudyHoursPerDay(Integer studyHoursPerDay) { this.studyHoursPerDay = studyHoursPerDay; }
public String getCleanlinessLevel() { return cleanlinessLevel; }
public void setCleanlinessLevel(String cleanlinessLevel) { this.cleanlinessLevel = cleanlinessLevel; }
public String getNoiseTolerance() { return noiseTolerance; }
public void setNoiseTolerance(String noiseTolerance) { this.noiseTolerance = noiseTolerance; }
public String getSocialPreference() { return socialPreference; }
public void setSocialPreference(String socialPreference) { this.socialPreference = socialPreference; }
}