package com.course.course.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.course.coures.dto.CategoryDTO;
import com.course.coures.dto.SubCategoryDTO;
import com.course.coures.response.CourseResponseDTO;
import com.course.course.model.Course;

public class CourseMapper1 {
	

	    public static CourseResponseDTO toDTO1(Course course) {
	        CourseResponseDTO dto = new CourseResponseDTO();
	        dto.setId(course.getId());
	        dto.setTitle(course.getTitle());
	        dto.setDescription(course.getDescription());
	        dto.setDuration(course.getDuration());
	        dto.setCategory(course.getCategory().getName());
	        dto.setSubCategory(course.getSubcategory().getName());
	        dto.setCreatorName(course.getCreator().getName());
	        dto.setReleaseDate(course.getReleaseDate());
	        dto.setAvailable(course.isAvailable());
	        dto.setStatus(course.getStatus());
	        dto.setPrice(course.getPrice());
	        dto.setLevel(course.getLevel());
	        dto.setLanguage(course.getLanguage());
	        dto.setPublished(course.isPublished());
	        dto.setThumbnailUrl(course.getThumbnailUrl());
	        // Populate category DTO with subcategories
	        CategoryDTO catDto = new CategoryDTO();
	        catDto.setId(course.getCategory().getId());
	        catDto.setName(course.getCategory().getName());
	        
	        /*  java7 
	         * List<SubCategoryDTO> subCategoryDTOList = new ArrayList<SubCategoryDTO>();
	        List<Subcategory> subcategories = course.getCategory().getSubcategories();

	        if (subcategories != null) {
	            for (Subcategory sub : subcategories) {
	                SubCategoryDTO subDto = new SubCategoryDTO();
	                subDto.setId(sub.getId());
	                subDto.setName(sub.getName());
	                subDto.setCategoryId(sub.getCategory().getId());
	                subDto.setCategoryName(sub.getCategory().getName());
	                subCategoryDTOList.add(subDto);
	            }
	        }

	        catDto.setSubcategories(subCategoryDTOList);
	        dto.setCategoryDto(catDto);
*/
	        // ✅ Add subcategories to categoryDto  java 8
	        List<SubCategoryDTO> subCategoryDTOList = course.getCategory().getSubcategories().stream()
	                .map(sub -> {
	                    SubCategoryDTO subDto = new SubCategoryDTO();
	                    subDto.setId(sub.getId());
	                    subDto.setName(sub.getName());
	                    subDto.setCategoryId(sub.getCategory().getId());
	                    subDto.setCategoryName(sub.getCategory().getName());
	                    return subDto;
	                }).collect(Collectors.toList());
	        catDto.setSubcategories(subCategoryDTOList);
	        dto.setCategoryDto(catDto);
	        
	        dto.setSubCategory(course.getSubcategory() != null ? course.getSubcategory().getName() : null);
	     // DTO object
	        if (course.getSubcategory() != null) {
	            SubCategoryDTO subDto = new SubCategoryDTO();
	            subDto.setId(course.getSubcategory().getId());
	            subDto.setName(course.getSubcategory().getName());
	            subDto.setCategoryId(course.getSubcategory().getCategory().getId());
	            subDto.setCategoryName(course.getSubcategory().getCategory().getName());
	            dto.setSubCategoryDto(subDto);
	        }
	        return dto;
	    }
	

}
