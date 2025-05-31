package com.course.course.service;

import com.course.coures.dto.CategoryDTO;
import com.course.course.mapper.CategoryMapper;
import com.course.course.model.Category;
import com.course.course.repository.CategoryRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;
//    @Autowired
//    private CategoryMapper categoryMapper;
    
    public List<CategoryDTO> getAllCategories() {
        return categoryRepo.findAll().stream()
                .map(CategoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CategoryDTO createCategory(CategoryDTO dto) {
        Category category = CategoryMapper.toEntity(dto);
        Category saved = categoryRepo.save(category);
        return CategoryMapper.toDTO(saved);
    }

   
    public CategoryDTO updateCategory(CategoryDTO dto) {
        Category existing = categoryRepo.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        existing.setName(dto.getName());
        return CategoryMapper.toDTO(categoryRepo.save(existing));
        
    }

    
    public void deleteCategory(Long id) {
    	categoryRepo.deleteById(id);
    }
}