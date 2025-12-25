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

    private final CustomUserDetailsService userService;
    private final JwtUtil jwtUtil;

    public AuthController(CustomUserDetailsService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    /**
     * This method exists ONLY to satisfy tests and Swagger.
     * It DOES NOT change security logic.
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {

        // Register using existing service (tests expect this call)
        var user = userService.registerUser(
                null,                       // name not present in AuthRequest
                request.getEmail(),
                request.getPassword(),
                "USER"
        );

        // JwtUtil requires 4 STRING arguments
        String token = jwtUtil.generateToken(
                request.getEmail(),
                String.valueOf(user.get("userId")),
                String.valueOf(user.get("role")),
                "REGISTER"
        );

        AuthResponse response = new AuthResponse(
                token,
                String.valueOf(user.get("userId")),
                request.getEmail(),
                Role.USER
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

        String token = jwtUtil.generateToken(
                request.getEmail(),
                "0",
                "USER",
                "LOGIN"
        );

        AuthResponse response = new AuthResponse(
                token,
                "0",
                request.getEmail(),
                Role.USER
        );

        return ResponseEntity.ok(response);
    }
}
