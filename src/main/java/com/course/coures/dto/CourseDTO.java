package com.course.coures.dto;

import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "Data transfer object for Course")



@Data

public class CourseDTO {

	private Long id;
	@Schema(description = "Title of the course", example = "Spring Boot Basics")
	@NotBlank(message = "Title is mandatory")
	private String title;
	@NotBlank(message = "Description is mandatory")
	@Schema(description = "Description of the course", example = "Introduction to Spring Boot")
	private String description;
	@Schema(description = "ID of the subcategory", example = "1")
	@NotNull(message = "Subcategory ID is required")
	private Long subcategoryId;
	private Long creatorId;
    private Set<Long> enrolledUserIds;
	// Getters and setters
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

	public Long getSubcategoryId() {
		return subcategoryId;
	}

	public void setSubcategoryId(Long subcategoryId) {
		this.subcategoryId = subcategoryId;
	}
}
