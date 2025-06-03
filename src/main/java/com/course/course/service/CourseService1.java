package com.course.course.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.course.coures.request.CourseRequestDTO;
import com.course.coures.response.CourseResponseDTO;
import com.course.course.mapper.CourseMapper1;
import com.course.course.model.Category;
import com.course.course.model.Course;
import com.course.course.model.Subcategory;
import com.course.course.model.User;
import com.course.course.repository.CategoryRepository;
import com.course.course.repository.CourseRepository;
import com.course.course.repository.SubcategoryRepository;
import com.course.course.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService1 {

	
	private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final UserRepository userRepository;

   
    public CourseResponseDTO createCourse1(CourseRequestDTO request) {
        // Find or create Category
        Category category = categoryRepository.findByName(request.getCategory())
                .orElseGet(() -> {
                    Category newCat = new Category();
                    newCat.setName(request.getCategory());
                    return categoryRepository.save(newCat);
                });

        // Find or create Subcategory
        Subcategory subcategory = subcategoryRepository.findByNameAndCategoryName(
                        request.getSubcategory(), request.getCategory())
                .orElseGet(() -> {
                    Subcategory sub = new Subcategory();
                    sub.setName(request.getSubcategory());
                    sub.setCategory(category);
                    return subcategoryRepository.save(sub);
                });

        // Fetch creator
        User creator = userRepository.findById(request.getCreatorId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Build Course
        Course course = new Course();
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setDuration(request.getDuration());
        course.setCategory(category);
        course.setSubcategory(subcategory);
        course.setCreator(creator);

        return CourseMapper1.toDTO1(courseRepository.save(course));
    }

   
    public List<CourseResponseDTO> getAllCourses1() {
        return courseRepository.findAll()
                .stream()
                .map(CourseMapper1::toDTO1)
                .collect(Collectors.toList());
    }
    
 
    public CourseResponseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + id));
        return CourseMapper1.toDTO1(course);
    }


    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // Reuse existing logic to find category and subcategory
        Category category = categoryRepository.findByName(request.getCategory())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Subcategory subcategory = subcategoryRepository.findByNameAndCategoryName(
                request.getSubcategory(), request.getCategory())
                .orElseThrow(() -> new RuntimeException("Subcategory not found"));

        User creator = userRepository.findById(request.getCreatorId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setDuration(request.getDuration());
        course.setCategory(category);
        course.setSubcategory(subcategory);
        course.setCreator(creator);

        return CourseMapper1.toDTO1(courseRepository.save(course));
    }

 
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        courseRepository.delete(course);
    }


    public Page<CourseResponseDTO> getCoursesPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return courseRepository.findAll(pageable)
                .map(CourseMapper1::toDTO1);
    }

}

