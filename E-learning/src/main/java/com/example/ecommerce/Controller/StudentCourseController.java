package com.example.ecommerce.Controller;

import com.example.ecommerce.Service.StudentCourseService;
import com.example.ecommerce.model.AuthModel;
import com.example.ecommerce.model.Course;
import com.example.ecommerce.model.StudentCourse;
//import com.example.ecommerce.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@CrossOrigin(origins = "https://majestic-rolypoly-d12d8d.netlify.app")
@RestController
@RequestMapping("/api/student-courses")
public class StudentCourseController {

    @Autowired
    private StudentCourseService studentCourseService;


    @PostMapping("/add-stud-course")
    public ResponseEntity<StudentCourse> addStudentCourse(@RequestBody Map<String, Long> request) {
        Long studentId = request.get("studentId");
        Long courseId = request.get("courseId");

        StudentCourse enrolled = studentCourseService.addCourseToStudent(studentId, courseId);
        return ResponseEntity.ok(enrolled);
    }

@GetMapping("/getCourse")
	public List<StudentCourse>getAllCourse(){
		return studentCourseService.getAllStudentCourses();
	}
@GetMapping("/get-course-student/{user_id}")
public List<Course> getCoursesByStudent(@PathVariable Long user_id) {
    return studentCourseService.getCoursesByStudentId(user_id);
}

}

