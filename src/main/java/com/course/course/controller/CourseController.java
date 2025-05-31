package com.course.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.coures.dto.CourseDTO;
import com.course.course.service.CourseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/courses")
@Tag(name = "Course", description = "Course management APIs")
public class CourseController {
    @Autowired
    private CourseService courseService;

    
    @GetMapping
    public List<CourseDTO> getAllCourses() {
        return courseService.getAllCourses();
    }

    @Operation(summary = "Create course")
    @PostMapping
    public CourseDTO createCourse( @Valid @RequestBody CourseDTO courseDTO) {
        return courseService.createCourse(courseDTO);
    }
//    @GetMapping("/pagination")
//    public Page<CourseDTO> getCourses(
//        @RequestParam(defaultValue = "0") int page,
//        @RequestParam(defaultValue = "10") int size,
//        @RequestParam(required = false) String keyword
//    ) {
//        return courseService.getCourses(page, size, keyword);
//    }
    @Operation(summary = "Update course by ID")
    @PutMapping("/{id}")
    public CourseDTO update(@PathVariable Long id, @Valid @RequestBody CourseDTO dto) {
        dto.setId(id);
        return courseService.update(dto);
    }
//    @Operation(summary = "Get all courses (paginated, filterable)")
//    @GetMapping
//    public Page<CourseDTO> getCourses(
//        @RequestParam(defaultValue = "0") int page,
//        @RequestParam(defaultValue = "10") int size,
//        @RequestParam(required = false) String keyword
//    ) {
//        return courseService.getCourses(page, size, keyword);
//    }
    
//    @Operation(summary = "Get paginated courses with optional keyword filter")
//    @GetMapping
//    public Page<CourseDTO> getCourses(
//            @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int page,
//            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int size,
//            @Parameter(description = "Search keyword") @RequestParam(required = false) String keyword) {
//        return courseService.getCourses(page, size, keyword);
//    }

   
}