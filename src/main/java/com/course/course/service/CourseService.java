package com.course.course.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.course.coures.dto.CourseDTO;
import com.course.course.mapper.CourseMapper;
import com.course.course.model.Course;
import com.course.course.model.Subcategory;
import com.course.course.repository.CourseRepository;
import com.course.course.repository.SubcategoryRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private SubcategoryRepository subcategoryRepo;
    
    @Autowired
    private CourseMapper mapper;

    public List<CourseDTO> getAllCourses() {
        return courseRepo.findAll().stream()
                .map(CourseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO createCourse(CourseDTO dto) {
        Subcategory subcategory = subcategoryRepo.findById(dto.getSubcategoryId())
                .orElseThrow(() -> new RuntimeException("Subcategory not found"));

        Course course = CourseMapper.toEntity(dto, subcategory);
        Course saved = courseRepo.save(course);
        return CourseMapper.toDTO(saved);
    }
    
    public Page<CourseDTO> getCourses(int page, int size, String keyword) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> coursePage;

        if (keyword != null && !keyword.isBlank()) {
            coursePage = courseRepo.findByTitleContainingIgnoreCase(keyword, pageable);
        } else {
            coursePage = courseRepo.findAll(pageable);
        }

        return coursePage.map(CourseMapper::toDTO);
    }
 // hwllo  hii  hello 
 
    public CourseDTO update(CourseDTO dto) {
        Course existing = courseRepo.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Course not found"));
        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        return mapper.toDTO(courseRepo.save(existing));
    }

// added commit to delete
    public void delete(Long id) {
        courseRepo.deleteById(id);
    }

}
