package com.course.course.mapper;

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
	        dto.setSubcategory(course.getSubcategory().getName());
	        dto.setCreatorName(course.getCreator().getName());
	        return dto;
	    }
	

}
