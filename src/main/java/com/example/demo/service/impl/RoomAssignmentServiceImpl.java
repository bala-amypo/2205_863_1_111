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
public class RoomAssignmentServiceImpl implements RoomAssignmentService {}

private final RoomAssignmentRecordRepository repo;
private final StudentProfileRepository studentRepo;

public RoomAssignmentServiceImpl(
RoomAssignmentRecordRepository repo,
StudentProfileRepository studentRepo) {
this.repo = repo;
this.studentRepo = studentRepo;
}

@Override
public RoomAssignmentRecord assignRoom(RoomAssignmentRecord assignment) {

StudentProfile a = studentRepo.findById(assignment.getStudentAId())
.orElseThrow(() -> new IllegalArgumentException("not found"));

StudentProfile b = studentRepo.findById(assignment.getStudentBId())
.orElseThrow(() -> new IllegalArgumentException("not found"));

if (!a.getActive() || !b.getActive()) {
throw new IllegalArgumentException("both students must be active");
}

assignment.setAssignedAt(LocalDateTime.now());
return repo.save(assignment);
}

@Override
public RoomAssignmentRecord updateStatus(Long id, String status) {
RoomAssignmentRecord record = getAssignmentById(id);
record.setStatus(status);
return repo.save(record);
}

@Override
public RoomAssignmentRecord getAssignmentById(Long id) {
return repo.findById(id)
.orElseThrow(() -> new IllegalArgumentException("not found"));
}

@Override
public List<RoomAssignmentRecord> getAssignmentsByStudent(Long studentId) {
return repo.findByStudentAIdOrStudentBId(studentId, studentId);
}

@Override
public List<RoomAssignmentRecord> getAllAssignments() {
return repo.findAll();
}
}