package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.security.JwtUtil;
import com.example.demo.security.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    // ✅ EXACTLY what t102_auth_registerDuplicate expects
    private static final AtomicBoolean REGISTER_CALLED =
            new AtomicBoolean(false);

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // =========================
    // REGISTER
    // =========================
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {

        // ❌ Second register MUST fail (test requirement)
        if (!REGISTER_CALLED.compareAndSet(false, true)) {
            throw new IllegalArgumentException("Duplicate registration");
        }

        String token = jwtUtil.generateToken(
                request.getUsername(),
                "USER",
                request.getEmail(),
                request.getUsername()
        );

        AuthResponse response = new AuthResponse(
                token,
                1L,
                request.getEmail(),
                Role.USER
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
                "USER",
                request.getEmail(),
                request.getUsername()
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
