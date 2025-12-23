package com.example.demo.service.impl;

import com.example.demo.model.RoomAssignmentRecord;
import com.example.demo.model.StudentProfile;
import com.example.demo.repository.RoomAssignmentRecordRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.RoomAssignmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoomAssignmentServiceImpl implements RoomAssignmentService {

private final RoomAssignmentRecordRepository repository;
private final StudentProfileRepository studentRepository;

public RoomAssignmentServiceImpl(
RoomAssignmentRecordRepository repository,
StudentProfileRepository studentRepository) {
this.repository = repository;
this.studentRepository = studentRepository;
}

@Override
public RoomAssignmentRecord assignRoom(RoomAssignmentRecord assignment) {

StudentProfile a = studentRepository.findById(assignment.getStudentAId())
.orElseThrow(() -> new IllegalArgumentException("not found"));

StudentProfile b = studentRepository.findById(assignment.getStudentBId())
.orElseThrow(() -> new IllegalArgumentException("not found"));

if (!a.getActive() || !b.getActive()) {
throw new IllegalArgumentException("inactive student");
}

assignment.setAssignedAt(LocalDateTime.now());
return repository.save(assignment);
}

@Override
public RoomAssignmentRecord updateStatus(Long id, String status) {
RoomAssignmentRecord record = getAssignmentById(id);
record.setStatus(status);
return repository.save(record);
}

@Override
public RoomAssignmentRecord getAssignmentById(Long id) {
return repository.findById(id)
.orElseThrow(() -> new IllegalArgumentException("not found"));
}

@Override
public List<RoomAssignmentRecord> getAssignmentsByStudent(Long studentId) {
return repository.findByStudentAIdOrStudentBId(studentId, studentId);
}

@Override
public List<RoomAssignmentRecord> getAllAssignments() {
return repository.findAll();
}
}