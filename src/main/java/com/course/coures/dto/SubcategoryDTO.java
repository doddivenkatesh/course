package com.course.coures.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SubcategoryDTO {
    private Long id;
     @NotBlank(message = "Name is mandatory")
    private String name;
     @NotNull(message = "Category ID is required")
    private Long categoryId;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
}