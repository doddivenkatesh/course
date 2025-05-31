package com.course.course.mapper;

import org.springframework.stereotype.Component;

import com.course.coures.dto.CourseDTO;
import com.course.course.model.Course;
import com.course.course.model.Subcategory;
@Component
public class CourseMapper {
    public static CourseDTO toDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setSubcategoryId(course.getSubcategory().getId());
        return dto;
    }

    public static Course toEntity(CourseDTO dto, Subcategory subcategory) {
        Course course = new Course();
        course.setId(dto.getId());
        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setSubcategory(subcategory);
        return course;
    }
}