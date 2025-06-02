package com.course.coures.request;

import com.course.coures.dto.CategoryDTO;

import lombok.Data;

@Data
public class SubcategoryResponseDTO {

	 private Long id;
	    private String name;
	    private CategoryDTO category;
}
