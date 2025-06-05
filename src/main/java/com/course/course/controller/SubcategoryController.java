package com.course.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.coures.dto.SubCategoryDTO;
import com.course.coures.request.SubcategoryRequestDTO;
import com.course.coures.response.ApiResponse;
import com.course.coures.response.SubcategoryResponseDTO;
import com.course.course.service.SubcategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/subcategories")
@Tag(name = "Subcategory", description = "Subcategory management APIs")
public class SubcategoryController {
    @Autowired
    private SubcategoryService subcategoryService;

    
    
    
    @PostMapping("create")
    public ResponseEntity<ApiResponse<SubcategoryResponseDTO>> createSubcategory(
            @RequestBody SubcategoryRequestDTO request) {
        SubcategoryResponseDTO dto = subcategoryService.createSubcategory(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Subcategory created successfully", dto));
    }

    @GetMapping("/getAll")
    public List<SubcategoryResponseDTO> getAll() {
        return subcategoryService.getAllSubcategories1();
    }
    
    @Operation(summary = "Get all subcategories")
    @GetMapping
    public List<SubCategoryDTO> getAllSubcategories() {
        return subcategoryService.getAllSubcategories();
    }
    @Operation(summary = "Create subcategory")
    @PostMapping
    public SubCategoryDTO createSubcategory(@Valid @RequestBody SubCategoryDTO dto) {
    	           
        return subcategoryService.createSubcategory(dto);
    }
    @Operation(summary = "Update subcategory by ID")
    @PutMapping("/{id}")
    public SubCategoryDTO update(@PathVariable Long id, @Valid @RequestBody SubCategoryDTO dto) {
        dto.setId(id);
        return subcategoryService.update(dto);
    }

    @Operation(summary = "Delete subcategory by ID")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        subcategoryService.delete(id);
    }
}