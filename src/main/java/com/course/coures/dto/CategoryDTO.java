package com.course.coures.dto;

import jakarta.validation.constraints.NotBlank;

//import jakarta.validation.constraints.NotBlank;
public class CategoryDTO {
    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}