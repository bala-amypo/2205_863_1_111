package com.example.demo.service;

import com.example.demo.model.RoomAssignmentRecord;

import java.util.List;

public interface RoomAssignmentService {

RoomAssignmentRecord assignRoom(RoomAssignmentRecord record);

RoomAssignmentRecord updateStatus(Long id, String status);

List<RoomAssignmentRecord> getAssignmentsForStudent(Long studentId);

RoomAssignmentRecord getById(Long id);

List<RoomAssignmentRecord> getAllAssignments();
}