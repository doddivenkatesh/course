package com.course.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.course.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
