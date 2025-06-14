package com.course.course.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.course.coures.dto.UserDTO;
import com.course.course.model.User;
import com.course.course.repository.UserRepository;
import com.course.course.service.UserService;
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired
	public UserService userService;
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
	    UserDTO userDTO = userService.findByUserId(id);
	    return ResponseEntity.ok(userDTO);
	}
	
	    @PostMapping
	    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
	        UserDTO createdUser = userService.createUser(userDTO);
	        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	    }
	    
	    @PutMapping("/{id}")
	    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
	        UserDTO updatedUser = userService.updateUser(id, userDTO);
	        return ResponseEntity.ok(updatedUser);
	    }
	     
//	     @GetMapping("/getAllUsers")
//	     public List<UserDTO> users = userService.getAllUsers1();
//	     List<UserDTO> users = userService.findAllUsers();
//	     return new ApiResponse<UserDTO>("Users fetched", users);
	    @GetMapping
	    public ResponseEntity<List<UserDTO>> getAllUsers() {
	        List<UserDTO> users = userService.findAllUsers();
	        return ResponseEntity.ok(users);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
	        userService.deleteUser(id);
	        return ResponseEntity.noContent().build();
	    }
	    
	    @GetMapping("/page")
	    public Page<User> getUsers(
	            @RequestParam(required = false) String name,
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "5") int size
	    ) {
	        return userService.getUsers(name, page, size);
	    }

	    @GetMapping("/pagination")
	    public ResponseEntity<Page<UserDTO>> getUsers1(
	            @RequestParam(defaultValue = "") String name,
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "10") int size) {
	        return ResponseEntity.ok(userService.getUsers1(name, page, size));
	    }
	    
	    @PostMapping("/upload")
	    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
	        try {
	            userService.saveUsersFromFile(file);
	            return ResponseEntity.ok("File uploaded and users saved successfully.");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed: " + e.getMessage());
	        }
	    }
	    
	    @PostMapping("/load-from-csv")
	    public ResponseEntity<String> loadUsers() {
	        try {
	            userService.loadUsersFromCSV();
	            return ResponseEntity.ok("Users loaded successfully from CSV");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error: " + e.getMessage());
	        }
	    }
	    
	    @PostMapping("/load-from-excel")
	    public ResponseEntity<String> loadUsersFromExcel() {
	        try {
	            userService.loadUsersFromExcel();
	            return ResponseEntity.ok("Users loaded from Excel successfully.");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error: " + e.getMessage());
	        }
	    }
	    
	    @GetMapping("/download-excel")
	    public ResponseEntity<Resource> downloadExcelFile() throws IOException {
	        ClassPathResource file = new ClassPathResource("Project-Management-Sample-Data.xlsx");

//	        HttpHeaders headers = new HttpHeaders();
//	        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=users.xlsx");
	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=users.xlsx")
	                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
	                .contentLength(file.contentLength())
	                .body(file);
//	        return ResponseEntity.ok()
//	                .headers(headers)
//	                .contentLength(file.contentLength())
//	                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
//	                .body(file);
	    }
	    
	    
	    @GetMapping("/download-users-csv")
	    public ResponseEntity<Resource> downloadUsersCsv() throws IOException {
	        List<User> users = userRepository.findAll();

	        StringWriter writer = new StringWriter();
	        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
	                .withHeader("ID", "Name", "Email", "Phone"));

	        for (User user : users) {
	            csvPrinter.printRecord(user.getId(), user.getName(), user.getEmail(), user.getAddress());
	        }

	        csvPrinter.flush();
	        ByteArrayInputStream csvInput = new ByteArrayInputStream(writer.toString().getBytes(StandardCharsets.UTF_8));

	        InputStreamResource file = new InputStreamResource(csvInput);

	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=customers-100.csv")
	                .contentType(MediaType.parseMediaType("text/csv"))
	                .body(file);
	    }
	    
	   /* @GetMapping("name")
	    //GET http://localhost:8080/api/users?name=John&email=gmail.com&address=NY&page=0&size=5&sortBy=email
	    public ResponseEntity<Page<UserDTO>> getFilteredUsers1(
	            @RequestParam(required = false, defaultValue = "") String name,
	            @RequestParam(required = false, defaultValue = "") String email,
	            @RequestParam(required = false, defaultValue = "") String address,
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "10") int size,
	            @RequestParam(defaultValue = "name") String sortBy) {

	        Page<UserDTO> result = userService.getFilteredUsers(name, email, address, page, size, sortBy);
	        return ResponseEntity.ok(result);
	    }

	  //  @GetMapping("/pa")
	    //GET /api/users?email=gmail.com
	    ///api/users?email=gmail.com&sortBy=name&sortDir=asc

//api/users?email=@yahoo.com&sortBy=email&sortDir=desc

	   /* public ResponseEntity<Page<UserDTO>> getFilteredUsers(
	            @RequestParam(required = false, defaultValue = "") String name,
	            @RequestParam(required = false, defaultValue = "") String email,
	            @RequestParam(required = false, defaultValue = "") String address,
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "10") int size,
	            @RequestParam(defaultValue = "name") String sortBy,
	            @RequestParam(defaultValue = "asc") String sortDir) {

	        Page<UserDTO> result = userService.getFilteredUsers(name, email, address, page, size, sortBy, sortDir);
	        return ResponseEntity.ok(result);
	    }

	  /*  @GetMapping("/email")
	    ///api/users?email=gmail.com&sortBy=email&sortDir=desc
	    public ResponseEntity<Page<UserDTO>> getFilteredUsers1(
	        @RequestParam(required = false, defaultValue = "") @Size(max = 50) String name,
	        @RequestParam(required = false, defaultValue = "") @Size(max = 50) String email,
	        @RequestParam(required = false, defaultValue = "") @Size(max = 100) String address,
	        @RequestParam(defaultValue = "0") @Min(0) int page,
	        @RequestParam(defaultValue = "10") @Min(1) int size,
	        @RequestParam(defaultValue = "name") @Pattern(regexp = "id|name|email|address", message = "sortBy must be id, name, email or address") String sortBy,
	        @RequestParam(defaultValue = "asc") @Pattern(regexp = "asc|desc", flags = Pattern.Flag.CASE_INSENSITIVE, message = "sortDir must be asc or desc") String sortDir
	    ) {
	        Page<UserDTO> result = userService.getFilteredUsers(name, email, address, page, size, sortBy, sortDir);
	        return ResponseEntity.ok(result);
	    }
}*/


// response for create
//{
//	  "name": "John Doe",
//	  "email": "john@example.com",
//	  "address": "123 Main Street",
//	  "createdCourseIds": [1, 2],
//	  "enrolledCourseIds": [3, 4]
//	}

// for the update 
//{
//	  "name": "John Updated",
//	  "email": "john.updated@example.com",
//	  "address": "456 New Address",
//	  "createdCourseIds": [1, 3],
//	  "enrolledCourseIds": [2, 4]
//	}
}