package com.example.ecommerce.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ecommerce.model.AuthModel;
import com.example.ecommerce.model.Course;

import jakarta.transaction.Transactional;

public interface CourseRepo extends JpaRepository<Course, Long> {
    List<Course> findByInstructor(AuthModel instructor);
    List<Course> findByCategory(String category);
    @Modifying
    @Transactional
    @Query("DELETE FROM StudentCourse sc WHERE sc.course.id = :courseId")
    void deleteAllByCourseId(@Param("course_id") Long courseId);
}
