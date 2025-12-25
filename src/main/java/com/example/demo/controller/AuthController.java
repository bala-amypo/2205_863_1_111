package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserAccountRepository userRepository;

    public AuthController(JwtUtil jwtUtil, UserAccountRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AuthRequest request) {

        // 1️⃣ Check if user already exists
        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        // 2️⃣ Convert DTO → Entity
        UserAccount user = new UserAccount();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword()); // BCrypt later
        user.setEmail(request.getEmail());
        user.setRole(
                request.getRole() != null ? request.getRole() : "USER"
        );

        // 3️⃣ Save user
        UserAccount savedUser = userRepository.save(user);

        // 4️⃣ Generate JWT
        String token = jwtUtil.generateToken(
                savedUser.getUsername(),
                savedUser.getRole(),
                savedUser.getEmail(),
                savedUser.getId().toString()
        );

        // 5️⃣ Return response
        return ResponseEntity.ok(
                Map.of(
                        "message", "User registered successfully",
                        "token", token
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {

        UserAccount user = userRepository
                .findByUsername(request.getUsername())
                .orElse(null);

        if (user == null || !user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        String token = jwtUtil.generateToken(
                user.getUsername(),
                user.getRole(),
                user.getEmail(),
                user.getId().toString()
        );

        return ResponseEntity.ok(Map.of("token", token));
    }
}
