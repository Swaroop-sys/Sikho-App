package com.example.ecommerce.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.Repository.AuthRepository;
import com.example.ecommerce.Repository.CourseRepo;
import com.example.ecommerce.model.AuthModel;
import com.example.ecommerce.model.Course;

@Service
public class CourseService {
	@Autowired
	private com.example.ecommerce.Repository.StudentCourseRepo studentCourseRepo;

    @Autowired
    private CourseRepo courseRepository;
//    @Autowired
//private AuthModel authModel;
    @Autowired
    private AuthRepository authRepository;

    public Course addCourse(Course course, String instructorEmail) {
        AuthModel instructor = authRepository.findByEmail(instructorEmail)
            .orElseThrow(() -> new RuntimeException("Instructor not found"));
        course.setInstructorName(instructor.getFullname()); // ✅ Use this

        course.setInstructor(instructor);
        return courseRepository.save(course);
    }

    public List<Course> getCoursesByInstructor(String email) {
        AuthModel instructor = authRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Instructor not found"));

        return courseRepository.findByInstructor(instructor);
    }
    public List<Course>getAllCourse(){
    	return courseRepository.findAll();
    }
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(()->new RuntimeException("Course Not Found"));
    }
    public List<Course> findByCategory(String categories){
    	return courseRepository.findByCategory(categories);
    }
    
    public boolean deleteById(Long id) {
        // Check if course exists
        Course course = courseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));

        // First delete from student-course mapping table
        studentCourseRepo.deleteByCourseId(id);

        // Then delete the course itself
        courseRepository.deleteById(id);

        return true;
    }

}

