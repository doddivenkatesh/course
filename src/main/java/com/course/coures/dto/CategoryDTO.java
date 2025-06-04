package com.course.coures.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//import jakarta.validation.constraints.NotBlank;
@Data
@Getter
@Setter
public class CategoryDTO {
	
    private Long id;
   // @NotBlank(message = "Name is mandatory")
    private String name;
    private List<SubCategoryDTO> subcategories ;
    // Getters and setters
    public Long getId() { return id;}
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}