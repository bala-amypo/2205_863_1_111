package com.example.demo.service;

import com.example.demo.model.MatchAttemptRecord;

import java.util.List;

public interface MatchAttemptService {

MatchAttemptRecord logAttempt(MatchAttemptRecord record);

MatchAttemptRecord updateStatus(Long id, String status);

MatchAttemptRecord getById(Long id);

List<MatchAttemptRecord> getByStudent(Long studentId);

List<MatchAttemptRecord> getAll();
}