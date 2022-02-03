package com.learnwithmohit.service;

import java.util.List;

import com.learnwithmohit.entity.User;

public interface UserService {
	User registerUser(User user);
	List<User> getAllUsers();
	User getUserById(long id);
	User updateUser(User user, long id);
	void deleteUser(long id);

	void initRoleAndUser();
}
