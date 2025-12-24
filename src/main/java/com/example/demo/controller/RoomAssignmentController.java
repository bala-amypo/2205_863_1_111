package com.example.demo.controller;

import com.example.demo.model.RoomAssignmentRecord;
import com.example.demo.service.RoomAssignmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room-assignments")
public class RoomAssignmentController {

    private final RoomAssignmentService service;

    public RoomAssignmentController(RoomAssignmentService service) {
        this.service = service;
    }

    // POST /
    @PostMapping
    public RoomAssignmentRecord assign(@RequestBody RoomAssignmentRecord record) {
        return service.assignRoom(record);
    }

    // PUT /{id}/status
    @PutMapping("/{id}/status")
    public RoomAssignmentRecord updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return service.updateStatus(id, status);
    }

    // GET /student/{studentId}
    @GetMapping("/student/{studentId}")
    public List<RoomAssignmentRecord> getByStudent(@PathVariable Long studentId) {
        return service.getAssignmentsByStudent(studentId);
    }

    // GET /{id}
    @GetMapping("/{id}")
    public RoomAssignmentRecord getById(@PathVariable Long id) {
        return service.getAssignmentById(id);
    }

    // GET /
    @GetMapping
    public List<RoomAssignmentRecord> getAll() {
        return service.getAllAssignments();
    }
}
