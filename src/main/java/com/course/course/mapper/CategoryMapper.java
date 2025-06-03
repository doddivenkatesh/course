package com.course.course.mapper;

import com.course.coures.dto.CategoryDTO;
import com.course.coures.dto.SubcategoryDTO;
import com.course.course.model.Category;

public class CategoryMapper {
    public static CategoryDTO toDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        SubcategoryDTO subcategoryDTO = new SubcategoryDTO();
        //dto.setSubcategoryDTO(category.getSubcategories().addAll(null));
       // subcategoryDTO.setId(category.getSubcategories().getId());
        //subcategoryDTO.setName(category.getSubcategories().getName());
        return dto;
    }

    public static Category toEntity(CategoryDTO dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        return category;
    }
}