package com.example.demo.controller;

import com.example.demo.model.RoomAssignmentRecord;
import com.example.demo.service.RoomAssignmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room-assignments")
@Tag(name = "Room Assignment")
public class RoomAssignmentController {

private final RoomAssignmentService service;

public RoomAssignmentController(RoomAssignmentService service) {
this.service = service;
}

@PostMapping
public ResponseEntity<RoomAssignmentRecord> assign(@RequestBody RoomAssignmentRecord record) {
return ResponseEntity.ok(service.assignRoom(record));
}

@GetMapping("/{id}")
public ResponseEntity<RoomAssignmentRecord> getById(@PathVariable Long id) {
return ResponseEntity.ok(service.getAssignmentById(id));
}

@GetMapping
public ResponseEntity<List<RoomAssignmentRecord>> getAll() {
return ResponseEntity.ok(service.getAllAssignments());
}

@GetMapping("/student/{studentId}")
public ResponseEntity<List<RoomAssignmentRecord>> getByStudent(@PathVariable Long studentId) {
return ResponseEntity.ok(service.getAssignmentsByStudent(studentId));
}

@PutMapping("/{id}/status")
public ResponseEntity<RoomAssignmentRecord> updateStatus(
@PathVariable Long id,
@RequestParam String status) {
return ResponseEntity.ok(service.updateStatus(id, status));
}
}