package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    public String generateToken(String email) {
        return "TOKEN_" + email;
    }

    public boolean validate(String token) {
        return token != null && token.startsWith("TOKEN_");
    }

    public String extractEmail(String token) {
        return token.replace("TOKEN_", "");
    }
}
