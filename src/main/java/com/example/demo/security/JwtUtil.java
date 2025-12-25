package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    // ✅ Base64-safe, JJWT-compatible key
    private static final SecretKey SECRET_KEY =
            Keys.hmacShaKeyFor("THIS_IS_A_VERY_SECURE_AND_LONG_SECRET_KEY_123456"
                    .getBytes());

    private static final long EXPIRATION_TIME =
            1000 * 60 * 60 * 24; // 24 hours

    // =========================
    // TOKEN GENERATION
    // =========================
    public String generateToken(
            String username,
            String role,
            String email,
            String userId
    ) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        claims.put("email", email);
        claims.put("userId", userId);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    // =========================
    // TOKEN PARSING
    // =========================
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token,
                                   Function<Claims, T> resolver) {
        return resolver.apply(getAllClaimsFromToken(token));
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // =========================
    // TOKEN VALIDATION (SECURITY)
    // =========================
    public boolean validateToken(String token, String username) {
        return username.equals(getUsernameFromToken(token))
                && !isTokenExpired(token);
    }

    // =========================
    // TOKEN VALIDATION (TESTS)
    // =========================
    public boolean validate(String token) {
        // ❗ Tests EXPECT exception for invalid token
        getAllClaimsFromToken(token);
        return true;
    }

    private boolean isTokenExpired(String token) {
        Date expiration = getClaimFromToken(token, Claims::getExpiration);
        return expiration.before(new Date());
    }
}
