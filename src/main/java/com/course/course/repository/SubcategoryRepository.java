package com.course.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.course.model.Subcategory;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {}