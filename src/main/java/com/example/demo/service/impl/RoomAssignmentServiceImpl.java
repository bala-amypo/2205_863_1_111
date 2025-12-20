package com.example.demo.service.impl;

import com.example.demo.model.RoomAssignmentRecord;
import com.example.demo.repository.RoomAssignmentRecordRepository;
import com.example.demo.service.RoomAssignmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoomAssignmentServiceImpl implements RoomAssignmentService {

private final RoomAssignmentRecordRepository repository;

public RoomAssignmentServiceImpl(RoomAssignmentRecordRepository repository) {
this.repository = repository;
}

@Override
public RoomAssignmentRecord assignRoom(RoomAssignmentRecord record) {
record.setAssignedAt(LocalDateTime.now());
return repository.save(record);
}

@Override
public RoomAssignmentRecord updateStatus(Long id, String status) {
RoomAssignmentRecord record = repository.findById(id)
.orElseThrow(() -> new IllegalArgumentException("not found"));
record.setStatus(status);
return repository.save(record);
}

@Override
public List<RoomAssignmentRecord> getAssignmentsForStudent(Long studentId) {
return repository.findByStudentId(studentId);
}

@Override
public RoomAssignmentRecord getById(Long id) {
return repository.findById(id)
.orElseThrow(() -> new IllegalArgumentException("not found"));
}

@Override
public List<RoomAssignmentRecord> getAllAssignments() {
return repository.findAll();
}
}