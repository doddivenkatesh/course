package com.course.coures.dto;

import java.util.List;
import java.util.Set;

import lombok.Data;

//UserDTO.java
@Data
public class UserDTO {
 //view
 
 private Long id;
 private String name;
 private String email;
 private String address;
 private List<Long> createdCourseIds;
 private Set<Long> enrolledCourseIds;
}

