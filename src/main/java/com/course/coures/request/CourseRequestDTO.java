package com.course.coures.request;

import java.time.LocalDate;

import com.course.course.course_enum.Status;

import lombok.Data;

@Data
public class CourseRequestDTO {

	   private String title;
	    private String description;
	    private String duration;
	    private String category;      // category name
	    private String subCategory;   // subcategory name
	    private Long creatorId;
	    private double price;
	    private LocalDate releaseDate;
	    private boolean available;
	    private Status status; 
	    private String language;
	    private String level;
	    private String thumbnailUrl;
	    private boolean published;
	    
	 
}

