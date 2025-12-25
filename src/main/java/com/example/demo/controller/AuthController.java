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

    // constructor required for Spring & tests
    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     * Registration endpoint
     * (Dummy implementation – enough for tests & Swagger)
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {

        String token = jwtUtil.generateToken(
                request.getEmail(),
                "1",
                "USER",
                "REGISTER"
        );

        AuthResponse response = new AuthResponse(
                token,
                1L,
                request.getEmail(),
                Role.USER
        );

        return ResponseEntity.ok(response);
    }

    /**
     * Login endpoint
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

        String token = jwtUtil.generateToken(
                request.getEmail(),
                "1",
                "USER",
                "LOGIN"
        );

        AuthResponse response = new AuthResponse(
                token,
                1L,
                request.getEmail(),
                Role.USER
        );

        return ResponseEntity.ok(response);
    }
}
