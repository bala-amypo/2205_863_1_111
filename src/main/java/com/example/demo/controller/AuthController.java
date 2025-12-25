package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final CustomUserDetailsService userService;
    private final JwtTokenProvider jwt;

    public AuthController(CustomUserDetailsService userService,
                          JwtTokenProvider jwt) {
        this.userService = userService;
        this.jwt = jwt;
    }

    // POST /auth/register
    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest req) {

        var user = userService.registerUser(
                req.getName(),
                req.getEmail(),
                req.getPassword(),
                req.getRole()
        );

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        req.getEmail(), req.getPassword());

        String token = jwt.generateToken(
                auth,
                (Long) user.get("userId"),
                (String) user.get("role"));

        return new AuthResponse(token);
    }

    // POST /auth/login
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest req) {

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        req.getEmail(), req.getPassword());

        String token = jwt.generateToken(auth, 0L, "USER");
        return new AuthResponse(token);
    }
}
