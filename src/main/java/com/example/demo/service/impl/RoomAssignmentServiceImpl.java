package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.RoomAssignmentRecord;
import com.example.demo.model.StudentProfile;
import com.example.demo.repository.RoomAssignmentRecordRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.RoomAssignmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomAssignmentServiceImpl implements RoomAssignmentService {

    private final RoomAssignmentRecordRepository roomRepo;
    private final StudentProfileRepository studentRepo;

    public RoomAssignmentServiceImpl(RoomAssignmentRecordRepository roomRepo,
                                     StudentProfileRepository studentRepo) {
        this.roomRepo = roomRepo;
        this.studentRepo = studentRepo;
    }

    @Override
    public RoomAssignmentRecord assignRoom(RoomAssignmentRecord assignment) {

        StudentProfile studentA = studentRepo.findById(assignment.getStudentAId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found"));

        StudentProfile studentB = studentRepo.findById(assignment.getStudentBId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found"));

        if (!Boolean.TRUE.equals(studentA.getActive())
                || !Boolean.TRUE.equals(studentB.getActive())) {
            throw new BadRequestException("Both students must be active");
        }

        assignment.setStatus(RoomAssignmentRecord.Status.ACTIVE);
        return roomRepo.save(assignment);
    }

    @Override
    public RoomAssignmentRecord updateStatus(Long id, String status) {

        RoomAssignmentRecord assignment = roomRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Assignment not found"));

        try {
            assignment.setStatus(RoomAssignmentRecord.Status.valueOf(status));
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("Invalid assignment status");
        }

        return roomRepo.save(assignment);
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
    public RoomAssignmentRecord getById(Long id) {
        return roomRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Assignment not found"));
    }
}
