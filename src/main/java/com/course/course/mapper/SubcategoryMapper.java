package com.course.course.mapper;

import org.springframework.stereotype.Component;

import com.course.coures.dto.CategoryDTO;
import com.course.coures.dto.SubCategoryDTO;
import com.course.course.model.Category;
import com.course.course.model.Subcategory;

@Component
public class SubcategoryMapper {
    public static SubCategoryDTO toDTO(Subcategory subcategory) {
        SubCategoryDTO dto = new SubCategoryDTO();
        dto.setId(subcategory.getId());
        dto.setName(subcategory.getName());
        
        CategoryDTO categoryDto = new CategoryDTO();
        categoryDto.setId(subcategory.getCategory().getId());
        categoryDto.setName(subcategory.getCategory().getName());
        dto.setCategoryId(subcategory.getCategory().getId());
        dto.setCategory(categoryDto);
        return dto;
    }

    public static Subcategory toEntity(SubCategoryDTO dto, Category category) {
        Subcategory subcategory = new Subcategory();
        subcategory.setId(dto.getId());
        subcategory.setName(dto.getName());
        subcategory.setCategory(category);
        return subcategory;
    }
}