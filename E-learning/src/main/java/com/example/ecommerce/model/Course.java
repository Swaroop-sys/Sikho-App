package com.example.ecommerce.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Course {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String title;

	    @Column(length = 2000)
	    private String description;

	    private String category;

	    private String thumbnailUrl;

	    private String videoUrl; 

	    private Double price;
	    private String instructorName;

	    @JsonIgnore
	    @ManyToOne
	    @JoinColumn(name = "instructor_id", nullable = false)
	    private AuthModel instructor; 

	    @CreationTimestamp
	    @Column(updatable = false)
	    private LocalDateTime createdAt;
	    public String getInstructorName() {
	        return instructorName;
	    }

	    public void setInstructorName(String instructorName) {
	        this.instructorName = instructorName;
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getThumbnailUrl() {
			return thumbnailUrl;
		}

		public void setThumbnailUrl(String thumbnailUrl) {
			this.thumbnailUrl = thumbnailUrl;
		}

		public String getVideoUrl() {
			return videoUrl;
		}

		public void setVideoUrl(String videoUrl) {
			this.videoUrl = videoUrl;
		}

		public Double getPrice() {
			return price;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

		public AuthModel getInstructor() {
			return instructor;
		}

		public void setInstructor(AuthModel instructor) {
			this.instructor = instructor;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

		public Course(Long id, String title, String description, String category, String thumbnailUrl, String videoUrl,
				Double price, AuthModel instructor, LocalDateTime createdAt) {
			super();
			this.id = id;
			this.title = title;
			this.description = description;
			this.category = category;
			this.thumbnailUrl = thumbnailUrl;
			this.videoUrl = videoUrl;
			this.price = price;
			this.instructor = instructor;
			this.createdAt = createdAt;
		}

		public Course() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
}
