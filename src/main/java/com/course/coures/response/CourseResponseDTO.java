package com.course.coures.response;

import java.time.LocalDate;

import com.course.coures.dto.CategoryDTO;
import com.course.coures.dto.SubCategoryDTO;
import com.course.course.course_enum.Status;

import lombok.Data;

@Data
public class CourseResponseDTO {
	private Long id;
	private String title;
	private String description;
	private String duration;
	private String category;
	private String subCategory;
	private String creatorName;
	private double price;
	private LocalDate releaseDate;
	private boolean available;
	private Status status;
	private String thumbnailUrl;
	private boolean published;
    private String language;
    private String level;
	private CategoryDTO categoryDto;
	private SubCategoryDTO subCategoryDto;
}

//private String title;
//private String description;
//private String duration;
//private String category;      // category name
//private String subcategory;   // subcategory name
//private Long creatorId;