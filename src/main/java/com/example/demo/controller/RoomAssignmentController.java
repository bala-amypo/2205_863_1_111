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

@PostMapping
public RoomAssignmentRecord assignRoom(@RequestBody RoomAssignmentRecord record) {
return service.assignRoom(record);
}

@PutMapping("/{id}/status")
public RoomAssignmentRecord updateStatus(
@PathVariable Long id,
@RequestParam String status
) {
return service.updateStatus(id, status);
}

@GetMapping("/student/{studentId}")
public List<RoomAssignmentRecord> getAssignmentsForStudent(
@PathVariable Long studentId
) {
return service.getAssignmentsForStudent(studentId);
}

@GetMapping("/{id}")
public RoomAssignmentRecord getById(@PathVariable Long id) {
return service.getById(id);
}

@GetMapping
public List<RoomAssignmentRecord> getAllAssignments() {
return service.getAllAssignments();
}
}