package com.learnwithmohit.controller;

import java.util.List;

import com.learnwithmohit.entity.User;
import com.learnwithmohit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

//	@PostConstruct
//	public void initRoleAndUser() {
//		userService.initRoleAndUser();
//	}

	@GetMapping
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") long userId){
		return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable("userId") long userId, @RequestBody User user){
		return new ResponseEntity<>(userService.updateUser(user, userId), HttpStatus.OK);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable("userId") long userId){
		userService.deleteUser(userId);
		return new ResponseEntity<>("User deleted successfully!.", HttpStatus.OK);
	}

	@GetMapping({"/forAdmin"})
	@PreAuthorize("hasRole('Admin')")
	public String forAdmin(){
		return "This URL is only accessible to the admin";
	}

	@GetMapping({"/forUser"})
	@PreAuthorize("hasRole('User')")
	public String forUser(){
		return "This URL is only accessible to the user";
	}
	
}
