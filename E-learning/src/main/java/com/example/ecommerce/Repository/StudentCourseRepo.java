package com.example.ecommerce.Repository;
import com.example.ecommerce.model.AuthModel;
import com.example.ecommerce.model.Course;
import com.example.ecommerce.model.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface StudentCourseRepo extends JpaRepository<StudentCourse, Long>{
	List<StudentCourse> findByStudent_UserId(Long userId);
	boolean existsByStudentAndCourse(AuthModel student, Course course);

//	
   
//	    boolean existsByStudentUser_idAndCourseId(Long studentId, Long courseId);

}
