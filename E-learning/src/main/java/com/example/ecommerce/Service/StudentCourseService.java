package com.example.ecommerce.Service;

import com.example.ecommerce.Repository.AuthRepository;
import com.example.ecommerce.Repository.CourseRepo;
import com.example.ecommerce.Repository.StudentCourseRepo;
import com.example.ecommerce.model.AuthModel;
import com.example.ecommerce.model.Course;
import com.example.ecommerce.model.StudentCourse;
//import com.example.ecommerce.repository.AuthRepository;
//import com.example.ecommerce.repository.CourseRepository;
//import com.example.ecommerce.repository.StudentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentCourseService {

    @Autowired
    private StudentCourseRepo studentCourseRepository;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private CourseRepo courseRepository;

    public StudentCourse addCourseToStudent(Long studentId, Long courseId) {
//       s
        AuthModel student = authRepository.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new RuntimeException("Course not found"));
        boolean alreadyEnrolled = studentCourseRepository.existsByStudentAndCourse(student, course);
        if (alreadyEnrolled) {
            throw new RuntimeException("Course already purchased by student");
        }
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setStudent(student);
        studentCourse.setCourse(course);

        return studentCourseRepository.save(studentCourse);
    }

  public List<StudentCourse>getAllStudentCourses(){
	  return studentCourseRepository.findAll();
  }
  public List<Course> getCoursesByStudentId(Long userId) {
      List<StudentCourse> enrolled = studentCourseRepository.findByStudent_UserId(userId);
      return enrolled.stream()
                     .map(StudentCourse::getCourse)
                     .collect(Collectors.toList());
  }

}
