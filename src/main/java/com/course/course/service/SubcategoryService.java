package com.course.course.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.coures.dto.CategoryDTO;
import com.course.coures.dto.SubCategoryDTO;
import com.course.coures.request.SubcategoryRequestDTO;
import com.course.coures.response.SubcategoryResponseDTO;
import com.course.course.exception.ResourceNotFoundException;
import com.course.course.mapper.SubcategoryMapper;
import com.course.course.model.Category;
import com.course.course.model.Subcategory;
import com.course.course.repository.CategoryRepository;
import com.course.course.repository.SubcategoryRepository;

@Service
public class SubcategoryService {

    @Autowired
    private SubcategoryRepository subcategoryRepo;

    @Autowired
    private CategoryRepository categoryRepo;
 
    @Autowired
    private SubcategoryMapper mapper;

    public List<SubCategoryDTO> getAllSubcategories() {
        return subcategoryRepo.findAll().stream()
                .map(SubcategoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    public SubCategoryDTO createSubcategory(SubCategoryDTO dto) {
        Category category = categoryRepo.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Subcategory subcategory = SubcategoryMapper.toEntity(dto, category);
        Subcategory saved = subcategoryRepo.save(subcategory);
        return SubcategoryMapper.toDTO(saved);
    }
    
    public SubcategoryResponseDTO createSubcategory(SubcategoryRequestDTO request) {
        Category category = categoryRepo.findById(request.getCategoryId())
            .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Subcategory subcategory = new Subcategory();
        subcategory.setName(request.getName());
        subcategory.setCategory(category);

        Subcategory saved = subcategoryRepo.save(subcategory);

        return mapToDTO(saved);
    }
    public List<SubcategoryResponseDTO> getAllSubcategories1() {
        return subcategoryRepo.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
 
    public SubCategoryDTO update(SubCategoryDTO dto) {
        Subcategory existing = subcategoryRepo.findById(dto.getId())
                .orElseThrow(() ->new ResourceNotFoundException("Subcategory not found"));
        existing.setName(dto.getName());
        return mapper.toDTO(subcategoryRepo.save(existing));
    }

    private SubcategoryResponseDTO mapToDTO(Subcategory subcategory) {
        SubcategoryResponseDTO dto = new SubcategoryResponseDTO();
        dto.setId(subcategory.getId());
        dto.setName(subcategory.getName());

        //SubcategoryResponseDTO.CategoryDTO catDto = new SubcategoryResponseDTO.CategoryDTO();
        CategoryDTO catDto = new CategoryDTO();
        catDto.setId(subcategory.getCategory().getId());
        catDto.setName(subcategory.getCategory().getName());
        
      

        //dto.setCategory(catDto);
        return dto;
    }
    public void delete(Long id) {
        subcategoryRepo.deleteById(id);
    }
}

