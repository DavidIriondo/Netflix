package com.everis.d4i.tutorial.security.services.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.everis.d4i.tutorial.security.entities.User;
import com.everis.d4i.tutorial.security.repositories.UserRepository;
import com.everis.d4i.tutorial.security.services.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Long id) {
		
		return userRepository.getOne(id);
	}

	@Override
	public User getUserByName(String name) {
		
		return userRepository.findByName(name);
	}
	
	@Override
	public User postUser(User user) {
		//Encrypting password
		user.setPassword(encoder.encode(user.getPassword()));
		//Saving new user
		userRepository.save(user);
		userRepository.flush();
		return user;
	}

	@Override
	public User deleteUserById(Long id) {
		
		User usr = userRepository.getOne(id);
		
		userRepository.delete(usr);
		userRepository.flush();
		return usr;
	}
	
	
	@Override
	public User updateUser(Long id, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByName(username);
		
		//Roles, here we need to retrieve the user´roles from data base
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ADMIN"));
		
		
		UserDetails userDetails =  new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), roles);
		
		return userDetails;
	}
	
	public void createUsers(String name) {
		
		User us = new User();
		
		us.setName(name);
		us.setPassword("1234");
		
		postUser(us);
		
	}

}