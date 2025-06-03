package com.course.coures.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Data;

//UserDTO.java
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserDTO {
 //view
 
 private Long id;
 private String name;
 private String email;
 private String address;
 private List<Long> createdCourseIds = new ArrayList<>();
 private Set<Long> enrolledCourseIds= new HashSet<>();
}

