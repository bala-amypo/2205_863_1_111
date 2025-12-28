package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {

        String role = request.getRole();
        if (role == null || role.isBlank()) {
            role = "USER";
        }

        String token = jwtUtil.generateToken(
                request.getEmail(),
                role,
                request.getEmail(),
                "1"
        );

        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {

        String role = request.getRole();
        if (role == null || role.isBlank()) {
            role = "USER";
        }

        String token = jwtUtil.generateToken(
                request.getEmail(),
                role,
                request.getEmail(),
                "1"
        );

        return ResponseEntity.ok(Map.of("token", token));
    }
}
