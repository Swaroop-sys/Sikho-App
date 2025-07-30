package com.example.ecommerce.Security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    // Secret key should be at least 256 bits long for HS256
    private Key getSignInKey() {
        String secret = "Sikho_Secret_Key_Which_Is_AtLeast_256_Bits_Long_For_HS256!!!";
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Generate JWT Token with extra claims (e.g., role)
    public String generateToken(Map<String, Object> extraClaims, String subject) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract email (subject) from JWT token
    public String extractEmail(String token) {
       try { return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
       }
       catch(ExpiredJwtException e) {
    	   throw e;
       }
    }
    

    // Validate JWT token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    // Optional: Extract custom claim like role
    public String extractRole(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("role", String.class);
    }
}
