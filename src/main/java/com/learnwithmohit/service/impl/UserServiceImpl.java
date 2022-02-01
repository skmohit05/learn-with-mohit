package com.learnwithmohit.service.impl;

import java.util.List;

import com.learnwithmohit.exception.ResourceNotFoundException;
import com.learnwithmohit.model.User;
import com.learnwithmohit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnwithmohit.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(long id) {
		return userRepository.findById(id).orElseThrow(() ->
						new ResourceNotFoundException("Employee", "Id", id));
		
	}

	@Override
	public User updateUser(User user, long id) {
		// we need to check whether user with given id is existing in DB or not
		User existingUser = userRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("User", "Id", id));
		
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		// save existing user to DB
		userRepository.save(existingUser);
		return existingUser;
	}

	@Override
	public void deleteUser(long id) {
		// check whether a user exist in a DB or not
		userRepository.findById(id).orElseThrow(() ->
								new ResourceNotFoundException("User", "Id", id));
		userRepository.deleteById(id);
	}
	
}
