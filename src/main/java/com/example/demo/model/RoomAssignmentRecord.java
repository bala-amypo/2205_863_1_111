package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RoomAssignmentRecord {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private Long studentId;
private String roomNumber;
private String status;
private LocalDateTime assignedAt;

public RoomAssignmentRecord() {}

public Long getId() {
return id;
}

public Long getStudentId() {
return studentId;
}

public void setStudentId(Long studentId) {
this.studentId = studentId;
}

public String getRoomNumber() {
return roomNumber;
}

public void setRoomNumber(String roomNumber) {
this.roomNumber = roomNumber;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public LocalDateTime getAssignedAt() {
return assignedAt;
}

public void setAssignedAt(LocalDateTime assignedAt) {
this.assignedAt = assignedAt;
}
}