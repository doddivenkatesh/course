package com.course.course.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.course.coures.dto.UserDTO;
import com.course.course.model.Course;
import com.course.course.model.User;

public class UserMapper {

	public static UserDTO toDTO(User user) {
		UserDTO dto = new UserDTO();
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setAddress(user.getAddress());
		// Convert created courses to IDs
		 if (user.getCreatedCourses() != null) {
		List<Long> createdCourseIds = user.getCreatedCourses().stream().map(Course::getId).collect(Collectors.toList());
		dto.setCreatedCourseIds(createdCourseIds);
		 }
		// Convert enrolled courses to IDs
		 if (user.getEnrolledCourses() != null) {
		Set<Long> enrolledCourseIds = user.getEnrolledCourses().stream().map(Course::getId).collect(Collectors.toSet());
		dto.setEnrolledCourseIds(enrolledCourseIds);
		 }
		return dto;
	}

	public static User toEntity(UserDTO dto, List<Course> createdCourses, Set<Course> enrolledCourses) {
		User user = new User();
		user.setId(dto.getId());
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setAddress(dto.getAddress());
		user.setCreatedCourses(createdCourses);
		user.setEnrolledCourses(enrolledCourses);
		return user;
	}

}


//public static UserDTO toDTO(User user) {
//    UserDTO dto = new UserDTO();
//    dto.setId(user.getId());
//    dto.setName(user.getName());
//    dto.setEmail(user.getEmail());
//    dto.setAddress(user.getAddress());
//
//    // Extract course IDs
//    List<Long> createdCourseIds = user.getCreatedCourses().stream()
//            .map(Course::getId)
//            .collect(Collectors.toList());
//
//    Set<Long> enrolledCourseIds = user.getEnrolledCourses().stream()
//            .map(Course::getId)
//            .collect(Collectors.toSet());
//
//    dto.setCreatedCourseIds(createdCourseIds);
//    dto.setEnrolledCourseIds(enrolledCourseIds);
//
//    return dto;
//}
