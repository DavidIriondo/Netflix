package com.netflix.project.security.services;

import java.util.List;

import com.netflix.project.security.entities.Role;
import com.netflix.project.security.entities.User;

public interface UserService {

	List<User> getAllUsers();
	
	User getUserById(Long id);
	
	User getUserByName(String name);
	
	List<Role> getUserRoles(Long id);
	
	User postUser(User user);
	
	User updateUser(Long id, User user);
	
	User deleteUserById(Long id);
}
