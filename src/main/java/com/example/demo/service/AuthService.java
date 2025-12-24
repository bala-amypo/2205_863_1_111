package com.example.demo.service;

import com.example.demo.dto.*;

public interface AuthService {
    AuthResponse register(AuthRequest request);
    AuthResponse login(AuthRequest request);
}
 