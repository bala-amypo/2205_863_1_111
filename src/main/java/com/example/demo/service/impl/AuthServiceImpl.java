package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.security.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {

    private final UserAccountRepository repository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserAccountRepository repository,
                           JwtUtil jwtUtil,
                           PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse register(AuthRequest request) {
        UserAccount user = new UserAccount();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.valueOf(request.getRole()));

        repository.save(user);

        return new AuthResponse(jwtUtil.generateToken(user.getUsername()));
    }

    public AuthResponse login(AuthRequest request) {
        UserAccount user = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        return new AuthResponse(jwtUtil.generateToken(user.getUsername()));
    }
}
