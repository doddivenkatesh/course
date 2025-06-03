package com.course.course.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.course.model.Subcategory;
import com.course.course.model.User;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {

	Optional<Subcategory> findByNameAndCategoryName(String subName, String categoryName);}