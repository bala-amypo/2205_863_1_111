package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class HabitProfileDto {}

@NotNull
private Long studentId;

@NotBlank
private String sleepSchedule;

@Min(0)
private Integer studyHoursPerDay;

@NotBlank
private String cleanlinessLevel;

@NotBlank
private String noiseTolerance;

@NotBlank
private String socialPreference;

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

public Integer getStudyHoursPerDay() {
return studyHoursPerDay;
}

public void setStudyHoursPerDay(Integer studyHoursPerDay) {
this.studyHoursPerDay = studyHoursPerDay;
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

public String getSocialPreference() {
return socialPreference;
}

public void setSocialPreference(String socialPreference) {
this.socialPreference = socialPreference;
}
}