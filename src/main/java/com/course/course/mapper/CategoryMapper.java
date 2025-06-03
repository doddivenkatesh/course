package com.course.course.mapper;

import java.util.ArrayList;
import java.util.List;

import com.course.coures.dto.CategoryDTO;
import com.course.coures.dto.SubcategoryDTO;
import com.course.course.model.Category;
import com.course.course.model.Subcategory;

public class CategoryMapper {
    public static CategoryDTO toDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        List<SubcategoryDTO> subcategoryDTOs = new ArrayList<>();
        if (category.getSubcategories() != null) {
            for (Subcategory sub : category.getSubcategories()) {
                SubcategoryDTO subDto = new SubcategoryDTO();
                subDto.setId(sub.getId());
                subDto.setName(sub.getName());
                subcategoryDTOs.add(subDto);
            }
        }

        dto.setSubcategories(subcategoryDTOs);
        return dto;
    }

    public static Category toEntity(CategoryDTO dto) {
        Category category = new Category();

        category.setName(dto.getName());
        if (dto.getSubcategories() != null) {
            List<Subcategory> subcategories = new ArrayList<>();
            for (SubcategoryDTO subDto : dto.getSubcategories()) {
                Subcategory sub = new Subcategory();
               
                sub.setName(subDto.getName());
                sub.setCategory(category); // set back reference
                subcategories.add(sub);
            }
            category.setSubcategories(subcategories);
        }
        return category;
    }
}