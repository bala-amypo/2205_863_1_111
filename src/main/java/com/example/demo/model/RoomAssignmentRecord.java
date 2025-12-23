package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "room_assignment_record")
public class RoomAssignmentRecord {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private Long studentAId;

private Long studentBId;

private String roomNumber;

private LocalDateTime assignedAt;

public RoomAssignmentRecord() {
this.assignedAt = LocalDateTime.now();
}

public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
}

public Long getStudentAId() {
return studentAId;
}

public void setStudentAId(Long studentAId) {
this.studentAId = studentAId;
}

public Long getStudentBId() {
return studentBId;
}

public void setStudentBId(Long studentBId) {
this.studentBId = studentBId;
}

public String getRoomNumber() {
return roomNumber;
}

public void setRoomNumber(String roomNumber) {
this.roomNumber = roomNumber;
}

public LocalDateTime getAssignedAt() {
return assignedAt;
}

public void setAssignedAt(LocalDateTime assignedAt) {
this.assignedAt = assignedAt;
}
}