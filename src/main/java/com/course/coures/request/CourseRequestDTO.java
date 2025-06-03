package com.course.coures.request;

import lombok.Data;

@Data
public class CourseRequestDTO {

	 private String title;
	    private String description;
	    private String duration;
	    private String category;      // category name
	    private String subcategory;   // subcategory name
	    private Long creatorId;
}
