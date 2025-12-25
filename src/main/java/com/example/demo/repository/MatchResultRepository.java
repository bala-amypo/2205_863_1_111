package com.example.demo.repository;

import com.example.demo.model.MatchResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchResultRepository extends JpaRepository<MatchResult, Long> {
    List<MatchResult> findByStudentAIdOrStudentBIdOrderByScoreDesc(Long id1, Long id2);
    Optional<MatchResult> findFirstByStudentAIdAndStudentBIdOrderByCreatedAtDesc(Long studentAId, Long studentBId);
}