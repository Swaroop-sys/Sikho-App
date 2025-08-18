package com.example.ecommerce.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.ecommerce.Enum.Role;
import com.example.ecommerce.Security.CustomUserDetailsService;
import com.example.ecommerce.Security.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers("/api/auth/**").permitAll()
            .requestMatchers("/api/course/message").permitAll()
            .requestMatchers("/api/course/delete-course-by-id/").hasRole(Role.INSTRUCTOR.toString())
            .requestMatchers("/api/course/get-by-category").hasRole(Role.STUDENT.toString())
            .requestMatchers("/api/student-courses/getCourse").permitAll()
            .requestMatchers("/api/student-courses/get-course-student/").hasRole(Role.STUDENT.name())
            .requestMatchers("/api/earning/total-earning").hasRole(Role.INSTRUCTOR.name())
            .requestMatchers("/api/earning/add-earning").hasRole(Role.STUDENT.name())
            .requestMatchers("/api/course/all-course").hasRole(Role.STUDENT.name())
            .requestMatchers("/api/student-courses/add-stud-course").hasRole(Role.STUDENT.name())
            .requestMatchers("/api/student-courses/get-by-email").hasRole(Role.STUDENT.name())
            .requestMatchers("/api/auth/stud-email").hasRole(Role.STUDENT.name())
            .requestMatchers("/api/course/get-by/").hasRole(Role.STUDENT.name())
            .requestMatchers("/api/course/add").hasRole(Role.INSTRUCTOR.name())
            .requestMatchers("/api/course/my-courses").hasRole(Role.INSTRUCTOR.name())
            .anyRequest().authenticated()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

}
