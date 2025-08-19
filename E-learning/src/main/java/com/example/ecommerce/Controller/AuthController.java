package com.example.ecommerce.Controller;

import java.util.List;
import java.util.Optional;

//package com.example.E-commerce.controller;
//
//import com.example.ecommerce.model.AuthModel;
//import com.example.ecommerce.service.AuthService;
//import com.example.ecommerce.exception.EmailAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ecommerce.Dto.LoginRequest;
import com.example.ecommerce.Dto.LoginResponse;
import com.example.ecommerce.Service.AuthService;
import com.example.ecommerce.model.AuthModel;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "https://spectacular-rugelach-ef3fb8.netlify.app")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthModel> registerUser(@RequestBody AuthModel authModel) {
        AuthModel registeredUser = authService.registerUser(authModel);
        return ResponseEntity.ok(registeredUser);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/stud-email/{email}")
  public Optional<AuthModel> getCoursesForStudent(@PathVariable String email) {
      return authService.getStudByEmail(email);
  }
}
