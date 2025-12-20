package com.example.demo.controller;

import com.example.demo.model.RoomAssignment;
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

@PostMapping
public RoomAssignment assignRoom(@RequestBody RoomAssignment assignment) {
return service.assignRoom(assignment);
}

@PutMapping("/{id}/status")
public RoomAssignment updateStatus(
@PathVariable Long id,
@RequestParam String status
) {
return service.updateStatus(id, status);
}

@GetMapping("/student/{studentId}")
public List<RoomAssignment> getAssignmentsForStudent(
@PathVariable Long studentId
) {
return service.getAssignmentsForStudent(studentId);
}

@GetMapping("/{id}")
public RoomAssignment getById(@PathVariable Long id) {
return service.getById(id);
}

@GetMapping
public List<RoomAssignment> getAllAssignments() {
return service.getAllAssignments();
}
}