package com.course.course.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.course.course.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	 Page<User> findByNameContainingIgnoreCase(String name, Pageable pageable);
	 Page<User> findByNameContainingIgnoreCaseAndEmailContainingIgnoreCaseAndAddressContainingIgnoreCase(
		        String name, String email, String address, Pageable pageable);

//	 @Query("SELECT u FROM User u LEFT JOIN FETCH u.createdCourses LEFT JOIN FETCH u.enrolledCourses WHERE u.id = :id")
//	 Optional<User> findByIdWithCourses(@Param("id") Long id);

	 @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.enrolledCourses LEFT JOIN FETCH u.createdCourses")
	 List<User> findAllWithCourses();

	   @EntityGraph(attributePaths = {"createdCourses", "enrolledCourses"})
	    Optional<User> findById(Long id);

	    @EntityGraph(attributePaths = {"createdCourses", "enrolledCourses"})
	    List<User> findAll();
	 @EntityGraph(attributePaths = {"createdCourses", "enrolledCourses"})
	 Page<User> findAll(Specification<User> spec, Pageable pageable);

}
