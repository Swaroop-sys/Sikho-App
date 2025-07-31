package com.example.ecommerce.Controller;

import java.util.List;

import com.example.ecommerce.model.Course;
import com.example.ecommerce.Service.CourseService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // ✅ Instructor uploads course
    @PostMapping("/add")
    public ResponseEntity<Course> addCourse(@RequestBody Course course, HttpServletRequest request) {
        String email = (String) request.getAttribute("email"); // set by JwtFilter
        return ResponseEntity.ok(courseService.addCourse(course, email));
    }

    // ✅ Instructor gets only their own uploaded courses
    @GetMapping("/my-courses")
    public ResponseEntity<List<Course>> getMyCourses(HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
        return ResponseEntity.ok(courseService.getCoursesByInstructor(email));
    }
    @GetMapping("/all-course")
    public List<Course> getAllCourse(){
    	return courseService.getAllCourse();
    }
    @GetMapping("/get-by/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }
    @GetMapping("/message")
    public String getMethodName(@RequestParam String param) {
        return "Hello";
    }
    
}
