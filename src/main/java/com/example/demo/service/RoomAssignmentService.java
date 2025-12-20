package com.example.demo.service;

import com.example.demo.model.RoomAssignment;

import java.util.List;

public interface RoomAssignmentService {

RoomAssignment assignRoom(RoomAssignment assignment);

RoomAssignment updateStatus(Long id, String status);

List<RoomAssignment> getAssignmentsForStudent(Long studentId);

RoomAssignment getById(Long id);

List<RoomAssignment> getAllAssignments();
}