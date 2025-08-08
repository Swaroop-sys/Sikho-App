package com.example.ecommerce.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.model.AuthModel;
import com.example.ecommerce.model.Course;

public interface CourseRepo extends JpaRepository<Course, Long> {
    List<Course> findByInstructor(AuthModel instructor);
    List<Course> findByCategory(String category);
}
