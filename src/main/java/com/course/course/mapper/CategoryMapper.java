package com.course.course.mapper;

import java.util.ArrayList;
import java.util.List;

import com.course.coures.dto.CategoryDTO;
import com.course.coures.dto.SubCategoryDTO;
import com.course.course.model.Category;
import com.course.course.model.Subcategory;

public class CategoryMapper {
    public static CategoryDTO toDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        List<SubCategoryDTO> subcategoryDTOs = new ArrayList<>();
        if (category.getSubcategories() != null) {
            for (Subcategory sub : category.getSubcategories()) {
                SubCategoryDTO subDto = new SubCategoryDTO();
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
            for (SubCategoryDTO subDto : dto.getSubcategories()) {
                Subcategory sub = new Subcategory();
                if (subDto.getId() != null) {
                    sub.setId(subDto.getId());
                }
               // sub.setId(subDto.getId());
                sub.setName(subDto.getName());
                sub.setCategory(category); // set back reference
                subcategories.add(sub);
            }
            category.setSubcategories(subcategories);
        }
        return category;
    }
    
    /*
     * update //
     * public static Category toEntity(CategoryDTO dto) {
        Category category = new Category();
        category.setId(dto.getId()); // For update scenario, set category ID
        category.setName(dto.getName());

        if (dto.getSubcategories() != null) {
            List<Subcategory> subcategories = new ArrayList<>();
            for (SubCategoryDTO subDto : dto.getSubcategories()) {
                Subcategory sub = new Subcategory();

                // Debug: print ID to trace issue
                System.out.println("SubCategoryDTO id: " + subDto.getId());

                // Only set ID if it's not null (for update)
                if (subDto.getId() != null) {
                    sub.setId(subDto.getId());
                }

                sub.setName(subDto.getName());
                sub.setCategory(category); // Maintain back reference
                subcategories.add(sub);
            }
            category.setSubcategories(subcategories);
        }

        return category;
    }*/
    
  /*  public void updateCategory(CategoryDTO dto) {
        Category category = categoryRepository.findById(dto.getId())
            .orElseThrow(() -> new RuntimeException("Category not found"));

        CategoryMapper.updateCategoryFromDTO(category, dto);
        categoryRepository.save(category); // updates, adds, and deletes subcategories
    }
    
    public static void updateCategoryFromDTO(Category category, CategoryDTO dto) {
        category.setName(dto.getName());

        List<Subcategory> updatedSubcategories = new ArrayList<>();

        if (dto.getSubcategories() != null) {
            // Track incoming subcategory IDs
            Set<Long> incomingIds = dto.getSubcategories().stream()
                .filter(s -> s.getId() != null)
                .map(SubCategoryDTO::getId)
                .collect(Collectors.toSet());

            // Remove subcategories not in DTO
            category.getSubcategories().removeIf(existing -> {
                return existing.getId() != null && !incomingIds.contains(existing.getId());
            });

            // Update existing or add new subcategories
            for (SubCategoryDTO subDto : dto.getSubcategories()) {
                Subcategory sub;

                if (subDto.getId() != null) {
                    // Update existing
                    sub = category.getSubcategories().stream()
                        .filter(existing -> existing.getId().equals(subDto.getId()))
                        .findFirst()
                        .orElse(new Subcategory()); // fallback in case of mismatch
                } else {
                    // New subcategory
                    sub = new Subcategory();
                }

                sub.setId(subDto.getId());
                sub.setName(subDto.getName());
                sub.setCategory(category);

                if (!category.getSubcategories().contains(sub)) {
                    category.getSubcategories().add(sub);
                }
            }
        }
    }
*/


}