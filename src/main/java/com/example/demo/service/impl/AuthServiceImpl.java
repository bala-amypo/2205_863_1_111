package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.security.Role;
import com.example.demo.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository repository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthServiceImpl(UserAccountRepository repository, JwtUtil jwtUtil) {
        this.repository = repository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public AuthResponse register(AuthRequest request) {
        repository.findByEmail(request.getEmail())
                .ifPresent(u -> { throw new IllegalArgumentException("exists"); });

        UserAccount user = new UserAccount();
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole(Role.valueOf(request.getRole()));
        user.setActive(true);

        user = repository.save(user);

        String token = jwtUtil.generateToken(
                user.getEmail(), user.getId(), user.getRole().name());

        return new AuthResponse(token, user.getId(), user.getEmail(), user.getRole().name());
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        UserAccount user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("invalid");
        }

        String token = jwtUtil.generateToken(
                user.getEmail(), user.getId(), user.getRole().name());

        return new AuthResponse(token, user.getId(), user.getEmail(), user.getRole().name());
    }
}
