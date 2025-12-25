package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalTime;
import java.time.LocalDateTime;

@Entity
@Table(name = "habit_profiles")
public class HabitProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;

    private Boolean smoking;
    private Boolean drinking;
    private LocalTime sleepTime;
    private LocalTime wakeTime;

    @Min(value = 1, message = "Cleanliness level must be in range 1-5")
    @Max(value = 5, message = "Cleanliness level must be in range 1-5")
    private Integer cleanlinessLevel;

    @Min(value = 1, message = "Noise preference must be in range 1-5")
    @Max(value = 5, message = "Noise preference must be in range 1-5")
    private Integer noisePreference;

    private String studyStyle;
    private String socialPreference;
    private String visitorsFrequency;

    @Min(value = 0, message = "Study hours must be >= 0")
    private Integer studyHoursPerDay;

    @Enumerated(EnumType.STRING)
    private SleepSchedule sleepSchedule;

    @Enumerated(EnumType.STRING)
    private CleanlinessLevel cleanlinessLevelEnum;

    @Enumerated(EnumType.STRING)
    private NoiseTolerance noiseTolerance;

    @Enumerated(EnumType.STRING)
    private SocialPreference socialPreferenceEnum;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public enum SleepSchedule {
        EARLY, REGULAR, LATE
    }

    public enum CleanlinessLevel {
        LOW, MEDIUM, HIGH
    }

    public enum NoiseTolerance {
        LOW, MEDIUM, HIGH
    }

    public enum SocialPreference {
        INTROVERT, BALANCED, EXTROVERT
    }

    public HabitProfile() {}

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Boolean getSmoking() { return smoking; }
    public void setSmoking(Boolean smoking) { this.smoking = smoking; }

    public Boolean getDrinking() { return drinking; }
    public void setDrinking(Boolean drinking) { this.drinking = drinking; }

    public LocalTime getSleepTime() { return sleepTime; }
    public void setSleepTime(LocalTime sleepTime) { this.sleepTime = sleepTime; }

    public LocalTime getWakeTime() { return wakeTime; }
    public void setWakeTime(LocalTime wakeTime) { this.wakeTime = wakeTime; }

    public Integer getCleanlinessLevel() { return cleanlinessLevel; }
    public void setCleanlinessLevel(Integer cleanlinessLevel) { this.cleanlinessLevel = cleanlinessLevel; }

    public Integer getNoisePreference() { return noisePreference; }
    public void setNoisePreference(Integer noisePreference) { this.noisePreference = noisePreference; }

    public String getStudyStyle() { return studyStyle; }
    public void setStudyStyle(String studyStyle) { this.studyStyle = studyStyle; }

    public String getSocialPreference() { return socialPreference; }
    public void setSocialPreference(String socialPreference) { this.socialPreference = socialPreference; }

    public String getVisitorsFrequency() { return visitorsFrequency; }
    public void setVisitorsFrequency(String visitorsFrequency) { this.visitorsFrequency = visitorsFrequency; }

    public Integer getStudyHoursPerDay() { return studyHoursPerDay; }
    public void setStudyHoursPerDay(Integer studyHoursPerDay) { this.studyHoursPerDay = studyHoursPerDay; }

    public SleepSchedule getSleepSchedule() { return sleepSchedule; }
    public void setSleepSchedule(SleepSchedule sleepSchedule) { this.sleepSchedule = sleepSchedule; }

    public CleanlinessLevel getCleanlinessLevelEnum() { return cleanlinessLevelEnum; }
    public void setCleanlinessLevelEnum(CleanlinessLevel cleanlinessLevelEnum) { this.cleanlinessLevelEnum = cleanlinessLevelEnum; }

    public NoiseTolerance getNoiseTolerance() { return noiseTolerance; }
    public void setNoiseTolerance(NoiseTolerance noiseTolerance) { this.noiseTolerance = noiseTolerance; }

    public SocialPreference getSocialPreferenceEnum() { return socialPreferenceEnum; }
    public void setSocialPreferenceEnum(SocialPreference socialPreferenceEnum) { this.socialPreferenceEnum = socialPreferenceEnum; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}