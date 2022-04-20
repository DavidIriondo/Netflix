package com.everis.d4i.tutorial.security.services;

import java.util.List;

import com.everis.d4i.tutorial.security.entities.Role;

public interface RoleService {
	
	List<Role> getAllRoles();
	
	Role getRoleById(Long id);
	
	Role postRole(Role user);
	
	Role updateRole(Long id, Role user);
	
	Role deleteRoleById(Long id);
	
	Role deleteRoleByType(String type);

}
