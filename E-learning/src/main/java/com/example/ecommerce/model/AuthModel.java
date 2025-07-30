package com.example.ecommerce.model;

import java.awt.List;
import java.util.ArrayList;

import com.example.ecommerce.Enum.Role;

import jakarta.persistence.CascadeType;
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
private Long user_id;
private String fullname;
private String email;
private String password;
@Enumerated(EnumType.STRING)
//@Column(nullable = false)
private Role role;
@OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
private java.util.List<Course> courses = new ArrayList<>();
public Long getUser_id() {
	return user_id;
}
public void setUser_id(Long user_id) {
	this.user_id = user_id;
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
public java.util.List<Course> getCourses() {
	return courses;
}
public void setCourses(java.util.List<Course> courses) {
	this.courses = courses;
}
public AuthModel() {
	super();
	// TODO Auto-generated constructor stub
}
public AuthModel(Long user_id, String fullname, String email, String password, Role role,
		java.util.List<Course> courses) {
	super();
	this.user_id = user_id;
	this.fullname = fullname;
	this.email = email;
	this.password = password;
	this.role = role;
	this.courses = courses;
}


}
