package com.course.course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.course.coures.dto.CourseDTO;
import com.course.course.mapper.CourseMapper;
import com.course.course.model.Course;
import com.course.course.model.Subcategory;
import com.course.course.repository.CourseRepository;
import com.course.course.repository.SubcategoryRepository;
import com.course.course.service.CourseService;

import jakarta.validation.constraints.AssertFalse.List;

@SpringBootTest
class CourseApplicationTests {

	@Test
	void contextLoads() {
	}
	
	 @Mock
	    private CourseRepository courseRepository;

	    @Mock
	    private SubcategoryRepository subcategoryRepository;

//	    @Mock
//	    private CourseMapper courseMapper;

	    @InjectMocks
	    private CourseService courseService;

//	    public CourseServiceTest() {
//	        MockitoAnnotations.openMocks(this);
//	    }

	    @Test
	    public void testGetCoursesWithKeyword() {
	        Pageable pageable = PageRequest.of(0, 10);
	        Course course = new Course();
	        course.setTitle("Spring Boot");
	        //Page<Course> page = new PageImpl<>(List.of(course));

	       // when(courseRepository.findByTitleContainingIgnoreCase("spring", pageable)).thenReturn(page);
	        when(CourseMapper.toDTO(any(Course.class))).thenReturn(new CourseDTO());

	        Page<CourseDTO> result = courseService.getCourses(0, 10, "spring");
	        assertEquals(1, result.getTotalElements());
	    }

	    @Test
	    public void testCreateCourse() {
	        CourseDTO dto = new CourseDTO();
	        dto.setTitle("New Course");
	        dto.setDescription("Desc");
	        dto.setSubcategoryId(1L);

	        Subcategory sub = new Subcategory();
	        Course course = new Course();

	        when(subcategoryRepository.findById(1L)).thenReturn(Optional.of(sub));
	       // when(CourseMapper.toEntity(dto)).thenReturn(course);
	        when(courseRepository.save(course)).thenReturn(course);
	        when(CourseMapper.toDTO(course)).thenReturn(dto);
	        

	        CourseDTO saved = courseService.createCourse(dto);
	        assertEquals("New Course", saved.getTitle());
	    }

	

}
