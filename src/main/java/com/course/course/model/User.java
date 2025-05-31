package com.course.course.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class User {

	

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;

	    // One user can create many courses
	    @OneToMany(mappedBy = "creator")
	    private List<Course> createdCourses = new ArrayList<>();

	    // Many users can enroll in many courses
	    @ManyToMany
	    @JoinTable(
	        name = "user_course_enrollments",
	        joinColumns = @JoinColumn(name = "user_id"),
	        inverseJoinColumns = @JoinColumn(name = "course_id")
	    )
	    private Set<Course> enrolledCourses = new HashSet<>();

	    // getters and setters
	

}
