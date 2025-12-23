package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "room_assignments")
public class RoomAssignmentRecord {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String roomNumber;
private Long studentAId;
private Long studentBId;
private String status;
private LocalDateTime assignedAt = LocalDateTime.now();

public Long getId() { return id; }
public String getRoomNumber() { return roomNumber; }
public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
public Long getStudentAId() { return studentAId; }
public void setStudentAId(Long studentAId) { this.studentAId = studentAId; }
public Long getStudentBId() { return studentBId; }
public void setStudentBId(Long studentBId) { this.studentBId = studentBId; }
public String getStatus() { return status; }
public void setStatus(String status) { this.status = status; }
}