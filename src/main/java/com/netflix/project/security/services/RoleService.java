package com.netflix.project.security.services;

import java.util.List;

import com.netflix.project.security.entities.Role;

public interface RoleService {
	
	List<Role> getAllRoles();
	
	Role getRoleById(Long id);
	
	Role postRole(Role user);
	
	Role updateRole(Long id, Role user);
	
	Role deleteRoleById(Long id);
	
	Role deleteRoleByType(String type);

}
