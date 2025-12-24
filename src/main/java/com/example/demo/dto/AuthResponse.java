package com.example.demo.dto;

public class AuthResponse {

    private int statusCodeValue;
    private String message;

    public AuthResponse(String message) {
        this.message = message;
        this.statusCodeValue = 200;
    }

    public int getStatusCodeValue() {
        return statusCodeValue;
    }

    public String getMessage() {
        return message;
    }
}
