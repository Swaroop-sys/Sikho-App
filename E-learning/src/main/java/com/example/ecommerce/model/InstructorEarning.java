package com.example.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class InstructorEarning {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@JsonIgnore
@ManyToOne
@JoinColumn(name = "instructor_id", nullable = false)
private AuthModel instructor; 


private Double earning;


public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}


public AuthModel getInstructor() {
	return instructor;
}


public void setInstructor(AuthModel instructor) {
	this.instructor = instructor;
}


public Double getEarning() {
	return earning;
}


public void setEarning(Double earning) {
	this.earning = earning;
}


public InstructorEarning(Long id, AuthModel instructor, Double earning) {
	super();
	this.id = id;
	this.instructor = instructor;
	this.earning = earning;
}


public InstructorEarning() {
	super();
}

}
