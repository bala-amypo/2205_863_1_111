// com/example/demo/service/impl/RoomAssignmentServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.RoomAssignmentRecord;
import com.example.demo.model.StudentProfile;
import com.example.demo.repository.RoomAssignmentRecordRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.RoomAssignmentService;

import java.util.List;

public class RoomAssignmentServiceImpl implements RoomAssignmentService {

private final RoomAssignmentRecordRepository roomRepo;
private final StudentProfileRepository studentRepo;

public RoomAssignmentServiceImpl(
RoomAssignmentRecordRepository roomRepo,
StudentProfileRepository studentRepo) {
this.roomRepo = roomRepo;
this.studentRepo = studentRepo;
}

@Override
public RoomAssignmentRecord assignRoom(RoomAssignmentRecord assignment) {
StudentProfile a = studentRepo.findById(assignment.getStudentAId())
.orElseThrow(() -> new ResourceNotFoundException("not found"));
StudentProfile b = studentRepo.findById(assignment.getStudentBId())
.orElseThrow(() -> new ResourceNotFoundException("not found"));

if (!a.getActive() || !b.getActive()) {
throw new IllegalArgumentException("both students must be active");
}
return roomRepo.save(assignment);
}

@Override
public RoomAssignmentRecord getAssignmentById(Long id) {
return roomRepo.findById(id)
.orElseThrow(() -> new ResourceNotFoundException("not found"));
}

@Override
public List<RoomAssignmentRecord> getAssignmentsByStudent(Long studentId) {
return roomRepo.findByStudentAIdOrStudentBId(studentId, studentId);
}

@Override
public List<RoomAssignmentRecord> getAllAssignments() {
return roomRepo.findAll();
}

@Override
public RoomAssignmentRecord updateStatus(Long id, String status) {
RoomAssignmentRecord record = getAssignmentById(id);
record.setStatus(status);
return roomRepo.save(record);
}
}