package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/security-demo")
public class SecurityDemoController {

    // ADMIN ONLY endpoint (for exam demo)
    @GetMapping("/admin")
    public ResponseEntity<String> adminOnly() {
        return ResponseEntity.ok("ADMIN ACCESS GRANTED");
    }

    // USER + ADMIN endpoint
    @GetMapping("/user")
    public ResponseEntity<String> userAccess() {
        return ResponseEntity.ok("USER ACCESS GRANTED");
    }
}
