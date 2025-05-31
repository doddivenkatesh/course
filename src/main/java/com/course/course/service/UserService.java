package com.course.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.course.model.Course;
import com.course.course.model.User;
import com.course.course.repository.CourseRepository;
import com.course.course.repository.UserRepository;

//UserService.java
@Service
public class UserService {

 @Autowired
 private UserRepository userRepository;

 @Autowired
 private CourseRepository courseRepository;

 public User createUser(String name) {
     User user = new User();
     user.setName(name);
     return userRepository.save(user);
 }

 public void enrollUserInCourse(Long userId, Long courseId) {
     User user = userRepository.findById(userId)
             .orElseThrow(() -> new RuntimeException("User not found"));
     Course course = courseRepository.findById(courseId)
             .orElseThrow(() -> new RuntimeException("Course not found"));
     user.getEnrolledCourses().add(course);
     userRepository.save(user);
 }

 public List<User> getAllUsers() {
     return userRepository.findAll();
 }
}

