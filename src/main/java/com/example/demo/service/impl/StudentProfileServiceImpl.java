package com.example.demo.service.impl;

import com.example.demo.model.StudentProfile;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

private final StudentProfileRepository repository;

public StudentProfileServiceImpl(StudentProfileRepository repository) {
this.repository = repository;
}

@Override
public StudentProfile createStudent(StudentProfile profile) {
return repository.save(profile);
}

@Override
public StudentProfile getStudentById(Long id) {
return repository.findById(id)
.orElseThrow(() -> new ResourceNotFoundException("student not found"));
}

@Override
public List<StudentProfile> getAllStudents() {
return repository.findAll();
}

@Override
public StudentProfile findByStudentId(String studentId) {
return repository.findByStudentId(studentId)
.orElseThrow(() -> new ResourceNotFoundException("student not found"));
}

@Override
public StudentProfile updateStudentStatus(Long id, boolean active) {
StudentProfile student = getStudentById(id);
student.setActive(active);
return repository.save(student);
}
}