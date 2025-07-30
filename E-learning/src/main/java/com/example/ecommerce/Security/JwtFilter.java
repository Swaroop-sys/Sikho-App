package com.example.ecommerce.Security;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        try {
        	if (authHeader != null && authHeader.startsWith("Bearer ")) {
        	    String token = authHeader.substring(7);
        	    String email = jwtUtil.extractEmail(token);

        	    if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        	        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        	        if (jwtUtil.validateToken(token)) {
        	            UsernamePasswordAuthenticationToken authenticationToken =
        	                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        	            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        	            request.setAttribute("email", email); // ✅ Pass email to controller
        	        }
        	    }
        	}


            filterChain.doFilter(request, response);

        } catch (ExpiredJwtException e) {
            // ✅ Handle token expiry
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Token Expired\"}");
        } catch (Exception e) {
            // ✅ Optional: Handle other token issues
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Invalid Token\"}");
        }
    }
}
