package com.example.demo.service.impl;

import com.example.demo.model.RoomAssignment;
import com.example.demo.repository.RoomAssignmentRepository;
import com.example.demo.service.RoomAssignmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoomAssignmentServiceImpl implements RoomAssignmentService {

    private final RoomAssignmentRepository repository;

        public RoomAssignmentServiceImpl(RoomAssignmentRepository repository) {
                this.repository = repository;
                    }

                        @Override
                            public RoomAssignment assignRoom(RoomAssignment assignment) {
                                    assignment.setAssignedAt(LocalDateTime.now());
                                            return repository.save(assignment);
                                                }

                                                    @Override
                                                        public RoomAssignment updateStatus(Long id, String status) {
                                                                RoomAssignment assignment = repository.findById(id)
                                                                                .orElseThrow(() -> new IllegalArgumentException("not found"));

                                                                                        assignment.setStatus(status);
                                                                                                return repository.save(assignment);
                                                                                                    }

                                                                                                        @Override
                                                                                                            public List<RoomAssignment> getAssignmentsForStudent(Long studentId) {
                                                                                                                    return repository.findByStudentId(studentId);
                                                                                                                        }

                                                                                                                            @Override
                                                                                                                                public RoomAssignment getById(Long id) {
                                                                                                                                        return repository.findById(id)
                                                                                                                                                        .orElseThrow(() -> new IllegalArgumentException("not found"));
                                                                                                                                                            }

                                                                                                                                                                @Override
                                                                                                                                                                    public List<RoomAssignment> getAllAssignments() {
                                                                                                                                                                            return repository.findAll();
                                                                                                                                                                                }
                                                                                                                                                                                }