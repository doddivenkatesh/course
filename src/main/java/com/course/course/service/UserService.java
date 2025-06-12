package com.course.course.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.course.coures.dto.UserDTO;
import com.course.course.mapper.UserMapper;
import com.course.course.model.Course;
import com.course.course.model.User;
import com.course.course.repository.CourseRepository;
import com.course.course.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
//UserService.java
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CourseRepository courseRepository;

	public UserDTO createUser(UserDTO dto) {
	    // Resolve createdCourses from courseRepository
	    List<Course> createdCourses = dto.getCreatedCourseIds().stream()
	            .map(id -> courseRepository.findById(id).orElse(null))
	            .filter(Objects::nonNull)
	            .collect(Collectors.toList());

	    // Resolve enrolledCourses from courseRepository
	    Set<Course> enrolledCourses = dto.getEnrolledCourseIds().stream()
	            .map(id -> courseRepository.findById(id).orElse(null))
	            .filter(Objects::nonNull)
	            .collect(Collectors.toSet());
	    
//	    List<Long> createdCourseIds = dto.getCreatedCourseIds() != null
//	    	    ? dto.getCreatedCourseIds()
//	    	    : new ArrayList<>();
//	    
//
//	    	Set<Long> enrolledCourseIds = dto.getEnrolledCourseIds() != null
//	    	    ? dto.getEnrolledCourseIds()
//	    	    : new HashSet<>();

	    // Convert DTO to entity
	    User user = UserMapper.toEntity(dto, createdCourses, enrolledCourses);

	    // Save user entity to the database
	    User saved = userRepository.save(user);

	    // Convert saved entity back to DTO and return
	    return UserMapper.toDTO(saved);
	}
	
	 

	public void enrollUserInCourse(Long userId, Long courseId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
		user.getEnrolledCourses().add(course);
		userRepository.save(user);
	}

	@Transactional(readOnly = true)
	public List<UserDTO> findAllUsers() {
		return userRepository.findAllWithCourses().stream()
				.map(UserMapper :: toDTO)
				.collect(Collectors.toList());
	}
	
	//If you want to stick with default findAll():
//	@Transactional(readOnly = true)
//	public List<UserDTO> findAllUsers() {
//	    List<User> users = userRepository.findAll();
//	    users.forEach(user -> {
//	        Hibernate.initialize(user.getEnrolledCourses());
//	        Hibernate.initialize(user.getCreatedCourses());
//	    });
//	    return users.stream()
//	                .map(UserMapper::toDTO)
//	                .collect(Collectors.toList());
//	}

//	public List<UserDTO> findAllUsers() {
//		return userRepository.findAll().stream()
//				.map(UserMapper :: toDTO)
//				.collect(Collectors.toList());
//	}
	
//	@Transactional(readOnly = true)
//	 public UserDTO findByUserId(Long id) {
//	        User user = userRepository.findById(id)
//	                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
//	       // Hibernate.initialize(user.getCreatedCourses());
//	       // Hibernate.initialize(user.getEnrolledCourses());
//	        return UserMapper.toDTO(user);
//	    }
	
	@Transactional(readOnly = true)
	public UserDTO findByUserId(Long id) {
	    User user = userRepository.findById(id)
	        .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
	    return UserMapper.toDTO(user);
	}

	/*@Transactional
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        // Eagerly fetch user along with createdCourses and enrolledCourses
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        // Update fields manually or with a mapping function
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setAddress(userDTO.getAddress());

        // Save updated user
        user = userRepository.save(user);

        // Convert to DTO including initialized collections
        return userMapper.toDTO(user);
    }*/
	@Transactional
	 public UserDTO updateUser(Long id, UserDTO dto) {
	        User existingUser = userRepository.findById(id)
	                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

	        existingUser.setName(dto.getName());
	        existingUser.setEmail(dto.getEmail());
	        existingUser.setAddress(dto.getAddress());

	        // Update created courses
	        List<Course> createdCourses = dto.getCreatedCourseIds().stream()
	                .map(courseRepository::findById)
	                .filter(Optional::isPresent)
	                .map(Optional::get)
	                .collect(Collectors.toList());

	        // Update enrolled courses
	        Set<Course> enrolledCourses = dto.getEnrolledCourseIds().stream()
	                .map(courseRepository::findById)
	                .filter(Optional::isPresent)
	                .map(Optional::get)
	                .collect(Collectors.toSet());

	        existingUser.setCreatedCourses(createdCourses);
	        existingUser.setEnrolledCourses(enrolledCourses);

	        User updatedUser = userRepository.save(existingUser);
	        return UserMapper.toDTO(updatedUser);
	    }
	 
	  public void deleteUser(Long id) {
	        User user = userRepository.findById(id)
	                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
	        userRepository.delete(user);
	    }
	  
	  public Page<User> getUsers(String name, int page, int size) {
	        PageRequest pageable = PageRequest.of(page, size);
	        if (name == null || name.isBlank()) {
	            return userRepository.findAll(pageable);
	        } else {
	            return userRepository.findByNameContainingIgnoreCase(name, pageable);
	        }
	    }
	  @Transactional(readOnly = true)	
	  public Page<UserDTO> getUsers1(String nameFilter, int page, int size) {
		    Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
		    //Page<User> users = userRepository.findByNameContainingIgnoreCase(nameFilter, pageable);
		    Page<User> users = userRepository.findAll(pageable); 
		    return users.map(UserMapper::toDTO);
		}
	  public Page<UserDTO> getFilteredUsers(String name, String email, String address, int page, int size, String sortBy) {
		    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());

		    Page<User> users = userRepository
		        .findByNameContainingIgnoreCaseAndEmailContainingIgnoreCaseAndAddressContainingIgnoreCase(
		            name != null ? name : "",
		            email != null ? email : "",
		            address != null ? address : "",
		            pageable
		        );

		    return users.map(UserMapper::toDTO);
		}
	
	  public Page<UserDTO> getFilteredUsers(
		        String name, String email, String address,
		        int page, int size, String sortBy, String sortDir) {

		    Sort.Direction direction = sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
		    Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

		    Page<User> users = userRepository
		        .findByNameContainingIgnoreCaseAndEmailContainingIgnoreCaseAndAddressContainingIgnoreCase(
		            name != null ? name : "",
		            email != null ? email : "",
		            address != null ? address : "",
		            pageable
		        );

		    return users.map(UserMapper::toDTO);
		}
	  
	  public Page<UserDTO> getFilteredUsers1(
		        String name, String email, String address,
		        int page, int size, String sortBy, String sortDir) {

		        // Sanitize inputs to avoid NPE
		        String safeName = (name == null) ? "" : name;
		        String safeEmail = (email == null) ? "" : email;
		        String safeAddress = (address == null) ? "" : address;

		        // Default to ascending if invalid
		        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? Sort.Direction.DESC : Sort.Direction.ASC;

		        // Validate sortBy: if empty or not a valid field, fallback to 'name'
		        if (!StringUtils.hasText(sortBy)) {
		            sortBy = "name";
		        }

		        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

		        Page<User> usersPage = userRepository.findByNameContainingIgnoreCaseAndEmailContainingIgnoreCaseAndAddressContainingIgnoreCase(
		            safeName, safeEmail, safeAddress, pageable);

		        return usersPage.map(UserMapper::toDTO);
		    }



	  public void saveUsersFromFile(MultipartFile file) throws Exception {
	        List<User> users = new ArrayList<>();

	        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
	            String line;
	            boolean isFirst = true;

	            while ((line = reader.readLine()) != null) {
	                if (isFirst) { // Skip header
	                    isFirst = false;
	                    continue;
	                }
	                String[] parts = line.split(",");

	                if (parts.length >= 3) {
	                    User user = new User();
	                    user.setName(parts[0].trim());
	                    user.setEmail(parts[1].trim());
	                    user.setAddress(parts[2].trim());
	                    //user.setPhone(parts[2].trim());
	                    users.add(user);
	                }
	            }

	            userRepository.saveAll(users);
	        } catch (IOException e) {
	            throw new Exception("Failed to parse file", e);
	        }
	    }
	  
	  
	  public void loadUsersFromCSV() throws Exception {
	        List<User> users = new ArrayList<>();

	        try (
	            InputStream is = getClass().getClassLoader().getResourceAsStream("customers-100.csv");
	            BufferedReader reader = new BufferedReader(new InputStreamReader(is))
	        ) {
	            String line;
	            boolean isFirstLine = true;

	            while ((line = reader.readLine()) != null) {
	                if (isFirstLine) { // skip header
	                    isFirstLine = false;
	                    continue;
	                }

	                String[] parts = line.split(",");
	                if (parts.length >= 3) {
	                    User user = new User();
	                    user.setName(parts[0].trim());
	                    user.setEmail(parts[1].trim());
	                    user.setAddress(parts[2].trim());
	                    //user.setPhone(parts[2].trim());
	                    users.add(user);
	                }
	            }

	            userRepository.saveAll(users);
	        } catch (IOException e) {
	            throw new Exception("Error reading CSV file", e);
	        }
	    }

	  public void loadUsersFromExcel() throws Exception {
	        List<User> users = new ArrayList<>();

	        try (InputStream is = getClass().getClassLoader().getResourceAsStream("Project-Management-Sample-Data.xlsx")) {
	            if (is == null) {
	                throw new FileNotFoundException("users.xlsx not found in resources");
	            }

	            Workbook workbook = new XSSFWorkbook(is);
	            Sheet sheet = workbook.getSheetAt(0);

	            boolean isFirstRow = true;
	            for (Row row : sheet) {
	                if (isFirstRow) {
	                    isFirstRow = false;
	                    continue; // Skip header
	                }

	                User user = new User();
	                user.setName(getCellValue(row.getCell(0)));
	                user.setEmail(getCellValue(row.getCell(1)));
	                user.setAddress(getCellValue(row.getCell(2)));
	                //user.setPhone(getCellValue(row.getCell(2)));
	                users.add(user);
	            }

	            workbook.close();
	            userRepository.saveAll(users);

	        } catch (IOException e) {
	            throw new Exception("Failed to read Excel file", e);
	        }
	    }

	    private String getCellValue(Cell cell) {
	        if (cell == null) return "";
	        return switch (cell.getCellType()) {
	            case STRING -> cell.getStringCellValue();
	            case NUMERIC -> String.valueOf((long) cell.getNumericCellValue());
	            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
	            default -> "";
	        };
	    }
}
