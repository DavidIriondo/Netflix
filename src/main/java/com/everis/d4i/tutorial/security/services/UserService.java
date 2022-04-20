package com.everis.d4i.tutorial.security.services;

import java.util.List;

import com.everis.d4i.tutorial.security.entities.User;

public interface UserService {

	List<User> getAllUsers();
	
	User getUserById(Long id);
	
	User getUserByName(String name);
	
	User postUser(User user);
	
	User updateUser(Long id, User user);
	
	User deleteUserById(Long id);
}
