package com.example.demo.service.Impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.StudentProfile;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;
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
if (repository.findByStudentId(profile.getStudentId()).isPresent()) {
throw new IllegalArgumentException("studentId exists");
}
return repository.save(profile);
}

@Override
public StudentProfile getStudentById(Long id) {
return repository.findById(id)
.orElseThrow(() -> new ResourceNotFoundException("not found"));
}

@Override
public List<StudentProfile> getAllStudents() {
return repository.findAll();
}
}