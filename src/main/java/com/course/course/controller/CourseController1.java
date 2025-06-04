package com.course.course.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.coures.request.CourseRequestDTO;
import com.course.coures.response.CourseResponseDTO;
import com.course.course.service.CourseService1;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController1 {

	private final CourseService1 courseService1;

    @PostMapping("/create")
    public ResponseEntity<CourseResponseDTO> createCourse1(@RequestBody CourseRequestDTO request) {
        return ResponseEntity.ok(courseService1.createCourse1(request));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses1() {
        return ResponseEntity.ok(courseService1.getAllCourses1());
    }
    
    @GetMapping("getById/{id}")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService1.getCourseById(id));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable Long id,
                                                          @RequestBody CourseRequestDTO request) {
        return ResponseEntity.ok(courseService1.updateCourse(id, request));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService1.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("pagination/page")
    //GET /api/courses/page?page=0&size=5
    public ResponseEntity<Page<CourseResponseDTO>> getCoursesPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(courseService1.getCoursesPaginated(page, size));
    }

}
