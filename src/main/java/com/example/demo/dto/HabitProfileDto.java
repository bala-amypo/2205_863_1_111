package com.example.demo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.time.LocalTime;

public class HabitProfileDto {
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

    public HabitProfileDto() {}

    // Getters and setters
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
}