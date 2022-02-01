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

	@GetMapping("{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") long employeeId){
		return new ResponseEntity<>(userService.getUserById(employeeId), HttpStatus.OK);
	}

	@PutMapping("{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user){
		return new ResponseEntity<>(userService.updateUser(user, id), HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") long id){
		// delete user from DB
		userService.deleteUser(id);
		return new ResponseEntity<>("User deleted successfully!.", HttpStatus.OK);
	}
	
}
