package com.learnwithmohit.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.learnwithmohit.exception.ResourceNotFoundException;
import com.learnwithmohit.entity.Role;
import com.learnwithmohit.entity.User;
import com.learnwithmohit.repository.RoleRepository;
import com.learnwithmohit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learnwithmohit.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User registerUser(User user) {
		Role role = roleRepository.findById("User").get();
		Set<Role> userRoles = new HashSet<>();
		userRoles.add(role);
		user.setRole(userRoles);
		user.setPassword(getEncodedPassword(user.getPassword()));

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

	@Override
	public void initRoleAndUser() {
		Role adminRole = new Role();
		adminRole.setRoleName("Admin");
		adminRole.setRoleDescription("Admin role");

		User adminUser = new User();
		adminUser.setUserName("admin123");
		adminUser.setPassword(getEncodedPassword("admin@pass"));
		adminUser.setFirstName("admin");
		adminUser.setLastName("admin");
		adminUser.setEmail("admin.learnwithmohit@gmail.com");
		adminUser.setPhone("+917395918464");
		Set<Role> adminRoles = new HashSet<>();
		adminRoles.add(adminRole);
		adminUser.setRole(adminRoles);
		userRepository.save(adminUser);

		Role userRole = new Role();
		userRole.setRoleName("User");
		userRole.setRoleDescription("Default role for newly created record");

		User user = new User();
		user.setUserName("skmohit05");
		user.setPassword(getEncodedPassword("asd@123"));
		user.setFirstName("mohit");
		user.setLastName("meena");
		user.setEmail("skmohit05@gmail.com");
		user.setPhone("+917395918464");
		Set<Role> userRoles = new HashSet<>();
		userRoles.add(userRole);
		user.setRole(userRoles);
		userRepository.save(user);
	}

	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}
	
}
