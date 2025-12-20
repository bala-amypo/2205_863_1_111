package com.example.demo.service;

import com.example.demo.model.StudentProfile;
import java.util.List;

public interface StudentProfileService {

StudentProfile create(StudentProfile student);

List<StudentProfile> getAll();

StudentProfile getById(Long id);
}