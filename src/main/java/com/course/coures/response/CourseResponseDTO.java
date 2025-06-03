package com.course.coures.response;

import lombok.Data;

@Data
public class CourseResponseDTO {
	 private Long id;
	    private String title;
	    private String description;
	    private String duration;
	    private String category;
	    private String subcategory;
	    private String creatorName;
	}
