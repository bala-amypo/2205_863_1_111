package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StudentProfileDto {

@NotBlank
private String studentId;

@NotBlank
private String fullName;

@Email
@NotBlank
private String email;

@NotBlank
private String department;

@NotNull
private Integer yearLevel;

public String getStudentId() {
return studentId;
}

public void setStudentId(String studentId) {
this.studentId = studentId;
}

public String getFullName() {
return fullName;
}

public void setFullName(String fullName) {
this.fullName = fullName;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getDepartment() {
return department;
}

public void setDepartment(String department) {
this.department = department;
}

public Integer getYearLevel() {
return yearLevel;
}

public void setYearLevel(Integer yearLevel) {
this.yearLevel = yearLevel;
}
}