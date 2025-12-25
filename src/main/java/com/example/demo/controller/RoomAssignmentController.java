package com.example.demo.controller;

import com.example.demo.model.RoomAssignmentRecord;
import com.example.demo.service.RoomAssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room-assignments")
public class RoomAssignmentController {
    
    private final RoomAssignmentService assignmentService;

    public RoomAssignmentController(RoomAssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping
    public ResponseEntity<RoomAssignmentRecord> assign(@RequestBody RoomAssignmentRecord assignment) {
        RoomAssignmentRecord created = assignmentService.assignRoom(assignment);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<RoomAssignmentRecord> updateStatus(@PathVariable Long id, @RequestParam String status) {
        RoomAssignmentRecord updated = assignmentService.updateStatus(id, status);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<RoomAssignmentRecord>> getByStudent(@PathVariable Long studentId) {
        List<RoomAssignmentRecord> assignments = assignmentService.getAssignmentsByStudent(studentId);
        return ResponseEntity.ok(assignments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomAssignmentRecord> getById(@PathVariable Long id) {
        RoomAssignmentRecord assignment = assignmentService.getAssignmentById(id);
        return ResponseEntity.ok(assignment);
    }

    @GetMapping
    public ResponseEntity<List<RoomAssignmentRecord>> getAll() {
        List<RoomAssignmentRecord> assignments = assignmentService.getAllAssignments();
        return ResponseEntity.ok(assignments);
    }
}