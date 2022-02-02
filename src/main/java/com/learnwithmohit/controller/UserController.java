package com.learnwithmohit.controller;

import java.util.List;

import com.learnwithmohit.model.User;
import com.learnwithmohit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping()
	public ResponseEntity<User> saveUser(@RequestBody User user){
		return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
	}

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
	
}
