package com.example.ecommerce.GlobalExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // This tells Spring to scan for exceptions globally
public class GlobalExceptionHandler {

    // Handles RuntimeException (like "Email already exists")
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Bad Request");
        response.put("message", ex.getMessage()); // Your custom message
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // You can add more handlers here for specific exceptions
}