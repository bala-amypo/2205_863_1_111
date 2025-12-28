package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.security.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserAccountRepository userRepo;

    public AuthController(JwtUtil jwtUtil,
                          UserAccountRepository userRepo) {
        this.jwtUtil = jwtUtil;
        this.userRepo = userRepo;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {

        // ✅ Duplicate check (required by tests)
        if (userRepo.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("User already exists");
        }

        UserAccount user = new UserAccount();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        // ✅ Convert Swagger role String → Enum
        Role role;
        try {
            role = request.getRole() != null
                    ? Role.valueOf(request.getRole())
                    : Role.USER;
        } catch (Exception e) {
            role = Role.USER;
        }

        user.setRole(role);
        userRepo.save(user);

        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name(),   // ✅ enum → String
                user.getEmail(),
                user.getId() != null ? user.getId().toString() : "1"
        );

        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {

        UserAccount user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid credentials"));

        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name(),   // ✅ enum → String
                user.getEmail(),
                user.getId().toString()
        );

        return ResponseEntity.ok(Map.of("token", token));
    }
}
