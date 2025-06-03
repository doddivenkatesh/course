package com.course.course.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String duration;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategory;
    // Creator (instructor)
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    // Enrolled users (students)
    @ManyToMany(mappedBy = "enrolledCourses")
    private Set<User> enrolledUsers = new HashSet<>();
    // Getters and setters
    
    
    
    
}
//✅ Use Example 1 (One-to-Many) if:
//	A user is the creator/owner of courses (like an instructor).
//
//	One User creates many Courses.
//
//	Each Course is owned by one User.
//
//	Example scenario:
//	User A created 5 different courses.
//
//	Each course has only 1 creator.
//
//	DB Design:
//	Course table has a user_id foreign key.

//Use Example 2 (Many-to-Many) if:
//Users enroll in courses, and each course has many users.
//
//One User can enroll in many Courses.
//
//One Course can have many enrolled Users.
//
//Example scenario:
//User A is enrolled in "Java" and "Python".
//
//"Java" course has 100 users enrolled.
//
//DB Design:
//You need a third table (join table): user_course to track enrollments.
//Feature	                      One-to-Many	Many-to-Many
//One user creates many courses	        ✅	        ❌
//Courses have only one owner	        ✅	        ❌
//Users enroll in multiple courses	    ❌  	    ✅
//Courses have many enrolled users	    ❌	        ✅
//Needs join table (extra table)	    ❌	        ✅ (user_course)