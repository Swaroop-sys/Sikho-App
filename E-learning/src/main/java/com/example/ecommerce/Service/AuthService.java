package com.example.ecommerce.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ecommerce.Dto.LoginRequest;
import com.example.ecommerce.Dto.LoginResponse;
import com.example.ecommerce.Repository.AuthRepository;
import com.example.ecommerce.Security.JwtUtil;
import com.example.ecommerce.model.AuthModel;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public AuthModel registerUser(AuthModel authModel) {
        // Check if email already exists
        if (authRepository.existsByEmail(authModel.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        authModel.setPassword(passwordEncoder.encode(authModel.getPassword()));
        return authRepository.save(authModel);
    }
    
    @Autowired
    private JwtUtil jwtUtil;


    public LoginResponse login(LoginRequest loginRequest) {
        AuthModel user = authRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with this Email"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().toString());
        claims.put("name", user.getFullname().toString());
        String token = jwtUtil.generateToken(claims, user.getEmail());

        System.out.println("Generated Token: " + token);
        System.out.println("Role is :"+user.getRole().name());
        return new LoginResponse(token, user.getRole().name());
    }
}
