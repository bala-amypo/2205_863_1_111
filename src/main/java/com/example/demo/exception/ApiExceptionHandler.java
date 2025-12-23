package com.example.demo.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

@ExceptionHandler(ResourceNotFoundException.class)
public String handleNotFound(ResourceNotFoundException ex) {
return ex.getMessage();
}

@ExceptionHandler(IllegalArgumentException.class)
public String handleIllegal(IllegalArgumentException ex) {
return ex.getMessage();
}
}