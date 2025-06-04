package com.course.course.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.course.course.model.Course;
import com.course.course.model.User;

public interface CourseRepository extends JpaRepository<Course, Long> {
	Page<Course> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);
	

	
	
	
	// @Query(value = "SELECT * FROM course WHERE name = :name", nativeQuery = true)
   // Course findByName(@Param("name") String name);
	
	//@Modifying
	//@Query(value = "INSERT INTO course(name, description, duration) VALUES (:name, :description, :duration)", nativeQuery = true)
	//void insertCourse(@Param("name") String name, @Param("description") String description, @Param("duration") String duration);
	
	//@Modifying
	//@Transactional
	//@Query(value = "UPDATE course SET name = :name WHERE id = :id", nativeQuery = true)
	//void updateCourseName(@Param("id") Long id, @Param("name") String name);

	//@Modifying
	//@Transactional
	//@Query(value = "DELETE FROM course WHERE id = :id", nativeQuery = true)
	//void deleteById(@Param("id") Long id);
	
	//@Query(value = "SELECT c.* FROM course c JOIN category cat ON c.category_id = cat.id WHERE cat.name = :categoryName", nativeQuery = true)
	//List<Course> findCoursesByCategoryName(@Param("categoryName") String categoryName);
     
	//@Query(value = "SELECT * FROM course ORDER BY id LIMIT :limit OFFSET :offset", nativeQuery = true)
	//List<Course> findCoursesPaginated(@Param("limit") int limit, @Param("offset") int offset);

	//@Query(value = "SELECT * FROM course", countQuery = "SELECT count(*) FROM course", nativeQuery = true)
	//Page<Course> findAllCoursesNative(Pageable pageable);

	//@Query(value = "SELECT COUNT(*) FROM course", nativeQuery = true)
	//int countAllCourses();

	//@Query(value = "SELECT COUNT(*) FROM course WHERE category_id = :categoryId", nativeQuery = true)
	//int countCoursesByCategory(@Param("categoryId") Long categoryId);

	//@Query(value = "SELECT SUM(price) FROM course", nativeQuery = true)
	//BigDecimal getTotalCourseRevenue();

	//@Query(value = """
		//	  SELECT c.*, cat.name AS category_name 
		//    FROM course c 
		//	  JOIN category cat ON c.category_id = cat.id
		//	  """, nativeQuery = true)
		//	List<Object[]> getCoursesWithCategoryNames();
	
	//@Query(value = """
	//		  SELECT c.* 
	//		  FROM course c 
	//		  JOIN course_enrolled_users ceu ON c.id = ceu.course_id 
	//		  WHERE ceu.user_id = :userId
	//		  """, nativeQuery = true)
	//		List<Course> findCoursesByEnrolledUser(@Param("userId") Long userId);

	//@Query(value = """
	//		  SELECT c.name, COUNT(ceu.user_id) AS enrolled_count 
	//		  FROM course c 
	//		  JOIN course_enrolled_users ceu ON c.id = ceu.course_id 
	//		  GROUP BY c.name
	//		  """, nativeQuery = true)
	//		List<Object[]> getEnrollmentCountPerCourse();

	
	// jpql query 
	//@Query("SELECT c FROM Course c WHERE c.name = :name")
	//Course findByName(@Param("name") String name);
	
	//@Modifying
	//@Transactional
	//@Query("UPDATE Course c SET c.name = :name WHERE c.id = :id")
	//void updateCourseName(@Param("id") Long id, @Param("name") String name);
	
	//@Modifying
	//@Transactional
	//@Query("DELETE FROM Course c WHERE c.id = :id")
	//void deleteById(@Param("id") Long id);
	
	//@Query("SELECT c FROM Course c JOIN c.category cat WHERE cat.name = :categoryName")
	//List<Course> findCoursesByCategoryName(@Param("categoryName") String categoryName);






}