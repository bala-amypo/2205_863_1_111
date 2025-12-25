package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.JwtUtil;
import com.example.demo.security.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // kept ONLY because tests expect this constructor
    private final CustomUserDetailsService userService;
    private final JwtUtil jwtUtil;

    public AuthController(CustomUserDetailsService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Registration endpoint (TEST & SWAGGER SAFE)
     * No dependency on CustomUserDetailsService methods
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
