package com.example.demo.controller;

import com.example.demo.model.RoomAssignmentRecord;
import com.example.demo.service.RoomAssignmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@Tag(name = "Room Assignment")
public class RoomAssignmentController {

private final RoomAssignmentService service;

public RoomAssignmentController(RoomAssignmentService service) {
this.service = service;
}

@PostMapping
public ResponseEntity<RoomAssignmentRecord> assignRoom()
@RequestParam Long studentAId,
@RequestParam Long studentBId,
@RequestParam String roomNumber) {
return ResponseEntity.ok(
service.assignRoom(studentAId, studentBId, roomNumber)
);
}

@GetMapping("/student/{studentId}")
public ResponseEntity<List<RoomAssignmentRecord>> getAssignmentsForStudent(
@PathVariable Long studentId) {
return ResponseEntity.ok(service.getAssignmentsForStudent(studentId));
}
}