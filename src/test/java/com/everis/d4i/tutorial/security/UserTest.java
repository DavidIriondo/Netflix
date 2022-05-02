package com.everis.d4i.tutorial.security;

import java.util.ArrayList;
import java.util.List;

import com.everis.d4i.tutorial.security.entities.Role;
import com.everis.d4i.tutorial.security.entities.User;
import com.everis.d4i.tutorial.security.services.Impl.RoleServiceImpl;
import com.everis.d4i.tutorial.security.services.Impl.UserServiceImpl;

public class UserTest {

	UserServiceImpl userServiceImpl;
	RoleServiceImpl roleServiceImpl;
	
	public UserTest( UserServiceImpl u, RoleServiceImpl r) {
		this.userServiceImpl = u;
		this.roleServiceImpl = r;
	}
	
	public void initSecurityUsers() {
		
		//Wee need to charge the different types of users(admin, user) into the database
		//Creating Roles
		Role userRole = new Role();
		userRole.setId(1L);
		userRole.setType("USER");
		
		Role adminRole = new Role();
		adminRole.setId(2L);
		adminRole.setType("ADMIN");
		
		roleServiceImpl.postRole(userRole);
		roleServiceImpl.postRole(adminRole);
		
		List<Role> userRoles = new ArrayList<>();
		userRoles.add(userRole);
		
		List<Role> adminRoles = new ArrayList<>();
		adminRoles.add(adminRole);
		
		//Creating Users
		
		User user = new User();
		user.setId(1L);
		user.setName("user");
		user.setPassword("1234");
		user.setRoles(userRoles);
		
		User admin = new User();
		admin.setId(2L);
		admin.setName("admin");
		admin.setPassword("1234");
		admin.setRoles(adminRoles);
		
		userServiceImpl.postUser(user);
		userServiceImpl.postUser(admin);
		
	}
}
