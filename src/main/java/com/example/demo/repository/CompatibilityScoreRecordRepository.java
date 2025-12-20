package com.example.demo.repository;

import com.example.demo.model.CompatibilityScoreRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompatibilityScoreRecordRepository
extends JpaRepository<CompatibilityScoreRecord, Long> {

List<CompatibilityScoreRecord> findByStudent1Id(Long student1Id);
}