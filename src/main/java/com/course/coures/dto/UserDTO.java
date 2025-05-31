package com.course.coures.dto;

import java.util.List;
import java.util.Set;

import lombok.Data;

//UserDTO.java
@Data
public class UserDTO {
 //use add changes
 
 private Long id;
 private String name;
 private List<Long> createdCourseIds;
 private Set<Long> enrolledCourseIds;
}

