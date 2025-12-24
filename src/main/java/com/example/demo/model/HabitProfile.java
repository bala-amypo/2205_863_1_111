package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class HabitProfile {

public enum SleepSchedule { EARLY, NORMAL, LATE }
public enum CleanlinessLevel { LOW, MEDIUM, HIGH }
public enum NoiseTolerance { LOW, MEDIUM, HIGH }
public enum SocialPreference { INTROVERT, BALANCED, EXTROVERT }

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Enumerated(EnumType.STRING)
private SleepSchedule sleepSchedule;

@Enumerated(EnumType.STRING)
private CleanlinessLevel cleanlinessLevel;

@Enumerated(EnumType.STRING)
private NoiseTolerance noiseTolerance;

@Enumerated(EnumType.STRING)
private SocialPreference socialPreference;

private LocalDateTime createdAt;
private LocalDateTime updatedAt;

public Long getId() { return id; }
public void setId(Long id) { this.id = id; }

public SleepSchedule getSleepSchedule() { return sleepSchedule; }
public void setSleepSchedule(SleepSchedule sleepSchedule) { this.sleepSchedule = sleepSchedule; }

public CleanlinessLevel getCleanlinessLevel() { return cleanlinessLevel; }
public void setCleanlinessLevel(CleanlinessLevel cleanlinessLevel) { this.cleanlinessLevel = cleanlinessLevel; }

public NoiseTolerance getNoiseTolerance() { return noiseTolerance; }
public void setNoiseTolerance(NoiseTolerance noiseTolerance) { this.noiseTolerance = noiseTolerance; }

public SocialPreference getSocialPreference() { return socialPreference; }
public void setSocialPreference(SocialPreference socialPreference) { this.socialPreference = socialPreference; }

public LocalDateTime getCreatedAt() { return createdAt; }
public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

public LocalDateTime getUpdatedAt() { return updatedAt; }
public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}