package com.course.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.course.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {}