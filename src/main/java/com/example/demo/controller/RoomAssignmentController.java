// com/example/demo/controller/RoomAssignmentController.java
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
public RoomAssignmentRecord assign(@RequestBody RoomAssignmentRecord record) {
return service.assignRoom(record);
}

@PutMapping("/{id}/status")
public RoomAssignmentRecord updateStatus(@PathVariable Long id,
@RequestParam String status) {
return service.updateStatus(id, status);
}

@GetMapping("/{id}")
public RoomAssignmentRecord getById(@PathVariable Long id) {
return service.getAssignmentById(id);
}

@GetMapping("/student/{studentId}")
public List<RoomAssignmentRecord> getByStudent(@PathVariable Long studentId) {
return service.getAssignmentsByStudent(studentId);
}

@GetMapping
public List<RoomAssignmentRecord> getAll() {
return service.getAllAssignments();
}
}