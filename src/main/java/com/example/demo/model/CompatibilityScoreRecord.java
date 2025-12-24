package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class CompatibilityScoreRecord {

    public enum CompatibilityLevel { LOW, MEDIUM, HIGH }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CompatibilityLevel compatibilityLevel;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public CompatibilityLevel getCompatibilityLevel() { return compatibilityLevel; }
    public void setCompatibilityLevel(CompatibilityLevel compatibilityLevel) {
        this.compatibilityLevel = compatibilityLevel;
    }
}
