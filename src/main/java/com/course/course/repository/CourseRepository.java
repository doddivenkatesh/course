package com.course.course.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.course.course.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
	Page<Course> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);

}