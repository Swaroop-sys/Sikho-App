package com.example.ecommerce.model;

import java.awt.List;
import java.util.ArrayList;

import com.example.ecommerce.Enum.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class AuthModel {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "user_id") // ✅ still maps to `user_id` in DB

private Long userId;
private String fullname;
private String email;
private String password;
@Enumerated(EnumType.STRING)
//@Column(nullable = false)
private Role role;
//@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
//private java.util.List<StudentCourse> enrolledCourses = new ArrayList<>();
//
//public java.util.List<StudentCourse> getEnrolledCourses() {
//	return enrolledCourses;
//}
//public void setEnrolledCourses(java.util.List<StudentCourse> enrolledCourses) {
//	this.enrolledCourses = enrolledCourses;
//}
//@OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
//private java.util.List<Course> courses = new ArrayList<>();
public Long getUser_id() {
	return userId;
}
public void setUser_id(Long user_id) {
	this.userId = userId;
}
public String getFullname() {
	return fullname;
}
public void setFullname(String fullname) {
	this.fullname = fullname;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public Role getRole() {
	return role;
}
public void setRole(Role role) {
	this.role = role;
}
//public java.util.List<Course> getCourses() {
//	return courses;
//}
//public void setCourses(java.util.List<Course> courses) {
//	this.courses = courses;
//}
public AuthModel() {
	super();
	// TODO Auto-generated constructor stub
}

public AuthModel(Long userId, String fullname, String email, String password, Role role) {
	super();
	this.userId = userId;
	this.fullname = fullname;
	this.email = email;
	this.password = password;
	this.role = role;
//	this.enrolledCourses = enrolledCourses;
//	this.courses = courses;
}


}
