package com.example.demo.repository;

import com.example.demo.model.RoomAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomAssignmentRepository extends JpaRepository<RoomAssignment, Long> {

List<RoomAssignment> findByStudentId(Long studentId);
}