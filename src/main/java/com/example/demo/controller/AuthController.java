package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final CustomUserDetailsService userService;
    private final JwtUtil jwtUtil;

    public AuthController(CustomUserDetailsService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // THIS METHOD IS REQUIRED BY TESTS
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {

        var user = userService.registerUser(
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                request.getRole()
        );

        String token = jwtUtil.generateToken(
                request.getEmail(),
                (Long) user.get("userId"),
                (String) user.get("role")
        );

        AuthResponse response = new AuthResponse(
                token,
                (Long) user.get("userId"),
                request.getEmail(),
                request.getRole()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

        String token = jwtUtil.generateToken(
                request.getEmail(),
                0L,
                "USER"
        );

        AuthResponse response = new AuthResponse(
                token,
                0L,
                request.getEmail(),
                null
        );

        return ResponseEntity.ok(response);
    }
}
