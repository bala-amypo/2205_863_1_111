package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtUtil;
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

        // ✅ DUPLICATE CHECK (FIXES t102)
        if (userRepo.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("User already exists");
        }

        // Create user
        UserAccount user = new UserAccount();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        // Swagger-controlled role (default USER)
        String role = request.getRole();
        if (role == null || role.isBlank()) {
            role = "USER";
        }
        user.setRole(role);

        userRepo.save(user);

        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole(),
                user.getEmail(),
                user.getId() != null ? user.getId().toString() : "1"
        );

        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {

        // Check user exists
        UserAccount user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid credentials"));

        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole(),
                user.getEmail(),
                user.getId().toString()
        );

        return ResponseEntity.ok(Map.of("token", token));
    }
}
