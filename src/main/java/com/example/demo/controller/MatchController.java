package com.example.demo.controller;

import com.example.demo.model.MatchResult;
import com.example.demo.service.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
@Tag(name = "Matches", description = "Compatibility matching")
public class MatchController {
    
    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping("/compute")
    @Operation(summary = "Compute compatibility match between two students")
    public ResponseEntity<MatchResult> computeMatch(@RequestParam Long studentAId,
                                                   @RequestParam Long studentBId) {
        MatchResult result = matchService.computeMatch(studentAId, studentBId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/student/{studentId}")
    @Operation(summary = "Get matches for a student")
    public ResponseEntity<List<MatchResult>> getMatchesForStudent(@PathVariable Long studentId) {
        List<MatchResult> matches = matchService.getMatchesFor(studentId);
        return ResponseEntity.ok(matches);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get match result by ID")
    public ResponseEntity<MatchResult> getMatch(@PathVariable Long id) {
        MatchResult match = matchService.getById(id);
        return ResponseEntity.ok(match);
    }
}