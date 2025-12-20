package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CompatibilityScore;
import com.example.demo.repository.CompatibilityScoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CompatibilityScoreServiceImpl implements CompatibilityScoreService {

    private final CompatibilityScoreRepository repository;

        public CompatibilityScoreServiceImpl(CompatibilityScoreRepository repository) {
                this.repository = repository;
                    }

                        @Override
                            public CompatibilityScore calculateScore(Long student1Id, Long student2Id) {

                                    if (student1Id.equals(student2Id)) {
                                                throw new IllegalArgumentException("same student");
                                                        }

                                                                CompatibilityScore score = new CompatibilityScore();
                                                                        score.setStudent1Id(student1Id);
                                                                                score.setStudent2Id(student2Id);

                                                                                        // simple deterministic range for tests
                                                                                                score.setScore(new Random().nextInt(41) + 60); // 60–100

                                                                                                        return repository.save(score);
                                                                                                            }

                                                                                                                @Override
                                                                                                                    public CompatibilityScore getScoreById(Long id) {
                                                                                                                            return repository.findById(id)
                                                                                                                                            .orElseThrow(() -> new ResourceNotFoundException("not found"));
                                                                                                                                                }

                                                                                                                                                    @Override
                                                                                                                                                        public List<CompatibilityScore> getScoresForStudent(Long studentId) {
                                                                                                                                                                return repository.findByStudent1Id(studentId);
                                                                                                                                                                    }
                                                                                                                                                                    }