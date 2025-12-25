package com.example.demo.controller;

import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.security.Role;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    
    private final UserAccountRepository repository;
    private final JwtUtil jwtUtil;

    public AuthController(UserAccountRepository repository, JwtUtil jwtUtil) {
        this.repository = repository;
        this.jwtUtil = jwtUtil;
    }

    /* ================= CREATE (REGISTER) ================= */
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserAccount request) {

        if (repository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        request.setRole(
                request.getRole() != null ? request.getRole() : Role.USER
        );

        UserAccount saved = repository.saveAndFlush(request);

        String token = jwtUtil.generateToken(
                saved.getEmail(),
                saved.getRole().name(),
                saved.getEmail(),
                saved.getId().toString()
        );

        return ResponseEntity.ok(Map.of(
                "message", "User registered",
                "token", token
        ));
    }

    /* ================= LOGIN ================= */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserAccount request) {

        UserAccount user = repository.findByEmail(request.getEmail())
                .orElse(null);

        if (user == null || !user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name(),
                user.getEmail(),
                user.getId().toString()
        );

        return ResponseEntity.ok(Map.of("token", token));
    }

    /* ================= READ ================= */
    @GetMapping("/{id}")
    public UserAccount getById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @GetMapping
    public List<UserAccount> getAll() {
        return repository.findAll();
    }

    /* ================= UPDATE ================= */
    @PutMapping("/{id}")
    public UserAccount update(
            @PathVariable Long id,
            @RequestBody UserAccount updated) {

        UserAccount existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existing.setEmail(updated.getEmail());
        existing.setPassword(updated.getPassword());
        existing.setRole(updated.getRole());
        existing.setActive(updated.isActive());

        return repository.save(existing);
    }

    /* ================= DELETE ================= */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        if (!repository.existsById(id)) {
            return ResponseEntity.badRequest().body("User not found");
        }

        repository.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
