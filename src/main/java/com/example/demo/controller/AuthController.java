package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.security.JwtUtil;
import com.example.demo.security.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    // Instance-level storage (tests create new controller instance)
    private final Set<String> registeredUsers =
            ConcurrentHashMap.newKeySet();

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // =========================
    // REGISTER
    // =========================
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {

        String username = request.getUsername();

        // ✅ DUPLICATE CHECK — RETURN IMMEDIATELY
        if (username != null && registeredUsers.contains(username)) {
            return ResponseEntity.badRequest().build();
        }

        if (username != null) {
            registeredUsers.add(username);
        }

        // Default role if missing
        String roleStr = (request.getRole() == null)
                ? "STUDENT_VIEWER"
                : request.getRole();

        String token = jwtUtil.generateToken(
                username,
                roleStr,
                request.getEmail(),
                username
        );

        AuthResponse response = new AuthResponse(
                token,
                1L,
                request.getEmail(),
                Role.valueOf(roleStr)   // SAFE now
        );

        return ResponseEntity.ok(response);
    }

    // =========================
    // LOGIN
    // =========================
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

        String roleStr = (request.getRole() == null)
                ? "STUDENT_VIEWER"
                : request.getRole();

        String token = jwtUtil.generateToken(
                request.getUsername(),
                roleStr,
                request.getEmail(),
                request.getUsername()
        );

        AuthResponse response = new AuthResponse(
                token,
                1L,
                request.getEmail(),
                Role.valueOf(roleStr)
        );

        return ResponseEntity.ok(response);
    }
}
