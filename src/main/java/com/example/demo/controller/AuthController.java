package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.security.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserAccountRepository userrepository;

    public AuthController(JwtUtil jwtUtil, UserAccountRepository userrepository) {
        this.jwtUtil = jwtUtil;
        this.userrepository = userrepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {

        if (userrepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("User already exists");
        }

        UserAccount user = new UserAccount();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(
                request.getRole() != null
                        ? Role.valueOf(request.getRole())
                        : Role.USER
        );

        UserAccount savedUser = userrepository.save(user);

        String token = jwtUtil.generateToken(
                savedUser.getEmail(),
                savedUser.getRole().name(),
                savedUser.getEmail(),
                savedUser.getId().toString()
        );

        return ResponseEntity.ok(
                Map.of("token", token)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {

        UserAccount user = userrepository
                .findByEmail(request.getEmail())
                .orElse(null);

        if (user == null || !user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name(),
                user.getEmail(),
                user.getId().toString()
        );

        return ResponseEntity.ok(Map.of("token", token));
    }
}
