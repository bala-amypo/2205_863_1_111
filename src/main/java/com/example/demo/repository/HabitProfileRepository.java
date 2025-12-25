package com.example.demo.repository;

import com.example.demo.model.HabitProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HabitProfileRepository extends JpaRepository<HabitProfile, Long> {
    Optional<HabitProfile> findByStudentId(Long studentId);
}