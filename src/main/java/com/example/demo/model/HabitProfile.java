package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(
name = "habit_profile",
uniqueConstraints = {
@UniqueConstraint(columnNames = "studentId")
}
)
public class HabitProfile {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private Long studentId;

private String sleepSchedule;

private String cleanlinessLevel;

private String noiseTolerance;

private String studyPreference;

private Boolean smoking;

private Boolean drinking;

public HabitProfile() {
}

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

public String getSleepSchedule() {
return sleepSchedule;
}

public void setSleepSchedule(String sleepSchedule) {
this.sleepSchedule = sleepSchedule;
}

public String getCleanlinessLevel() {
return cleanlinessLevel;
}

public void setCleanlinessLevel(String cleanlinessLevel) {
this.cleanlinessLevel = cleanlinessLevel;
}

public String getNoiseTolerance() {
return noiseTolerance;
}

public void setNoiseTolerance(String noiseTolerance) {
this.noiseTolerance = noiseTolerance;
}

public String getStudyPreference() {
return studyPreference;
}

public void setStudyPreference(String studyPreference) {
this.studyPreference = studyPreference;
}

public Boolean getSmoking() {
return smoking;
}

public void setSmoking(Boolean smoking) {
this.smoking = smoking;
}

public Boolean getDrinking() {
return drinking;
}

public void setDrinking(Boolean drinking) {
this.drinking = drinking;
}
}