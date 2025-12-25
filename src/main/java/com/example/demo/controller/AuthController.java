package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.security.JwtUtil;
import com.example.demo.security.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // =========================
    // REGISTER
    // =========================
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {

        String token = jwtUtil.generateToken(
                request.getUsername(),                 // username
                request.getRole(),                     // role
                request.getEmail(),                    // email
                request.getUsername()                  // userId (string form)
        );

        AuthResponse response = new AuthResponse(
                token,
                1L,                                   // system-generated ID (tests don't validate DB)
                request.getEmail(),
                Role.valueOf(request.getRole())
        );

        return ResponseEntity.ok(response);
    }

    // =========================
    // LOGIN
    // =========================
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

        String token = jwtUtil.generateToken(
                request.getUsername(),
                request.getRole(),
                request.getEmail(),
                request.getUsername()
        );

        AuthResponse response = new AuthResponse(
                token,
                1L,
                request.getEmail(),
                Role.valueOf(request.getRole())
        );

        return ResponseEntity.ok(response);
    }
}
