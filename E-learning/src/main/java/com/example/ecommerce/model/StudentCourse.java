package com.example.ecommerce.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class StudentCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private AuthModel student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime EnrollAt;
	public StudentCourse() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public AuthModel getStudent() {
		return student;
	}
	public void setStudent(AuthModel student) {
		this.student = student;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public LocalDateTime getEnrollAt() {
		return EnrollAt;
	}
	public void setEnrollAt(LocalDateTime enrollAt) {
		EnrollAt = enrollAt;
	}
	public StudentCourse(Long id, AuthModel student, Course course, LocalDateTime enrollAt) {
		super();
		this.id = id;
		this.student = student;
		this.course = course;
		EnrollAt = enrollAt;
	}
    
}
