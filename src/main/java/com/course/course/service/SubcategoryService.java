package com.course.course.service;

import com.course.coures.dto.SubcategoryDTO;
import com.course.course.exception.ResourceNotFoundException;
import com.course.course.mapper.SubcategoryMapper;
import com.course.course.model.Category;
import com.course.course.model.Subcategory;
import com.course.course.repository.CategoryRepository;
import com.course.course.repository.SubcategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubcategoryService {

    @Autowired
    private SubcategoryRepository subcategoryRepo;

    @Autowired
    private CategoryRepository categoryRepo;
 
    @Autowired
    private SubcategoryMapper mapper;

    public List<SubcategoryDTO> getAllSubcategories() {
        return subcategoryRepo.findAll().stream()
                .map(SubcategoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    public SubcategoryDTO createSubcategory(SubcategoryDTO dto) {
        Category category = categoryRepo.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Subcategory subcategory = SubcategoryMapper.toEntity(dto, category);
        Subcategory saved = subcategoryRepo.save(subcategory);
        return SubcategoryMapper.toDTO(saved);
    }
    
 
    public SubcategoryDTO update(SubcategoryDTO dto) {
        Subcategory existing = subcategoryRepo.findById(dto.getId())
                .orElseThrow(() ->new ResourceNotFoundException("Subcategory not found"));
        existing.setName(dto.getName());
        return mapper.toDTO(subcategoryRepo.save(existing));
    }

  
    public void delete(Long id) {
        subcategoryRepo.deleteById(id);
    }
}

