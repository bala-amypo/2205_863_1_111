package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.StudentProfile;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository studentRepo;

    public StudentProfileServiceImpl(StudentProfileRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public StudentProfile createStudent(StudentProfile student) {

        if (studentRepo.findByStudentId(student.getStudentId()).isPresent()) {
            throw new BadRequestException("StudentId already exists");
        }

        if (studentRepo.findByEmail(student.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }

        return studentRepo.save(student);
    }

    @Override
    public StudentProfile getStudentById(Long id) {
        return studentRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found"));
    }

    @Override
    public StudentProfile updateStatus(Long id, boolean active) {
        StudentProfile student = getStudentById(id);
        student.setActive(active);
        return studentRepo.save(student);
    }

    @Override
    public List<StudentProfile> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public StudentProfile getByStudentId(String studentId) {
        return studentRepo.findByStudentId(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found"));
    }
}
