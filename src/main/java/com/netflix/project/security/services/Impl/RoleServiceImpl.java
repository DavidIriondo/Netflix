package com.netflix.project.security.services.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.project.security.entities.Role;
import com.netflix.project.security.repositories.RoleRepository;
import com.netflix.project.security.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> getAllRoles() {
		
		return roleRepository.findAll();
	}

	@Override
	public Role getRoleById(Long id) {
		
		return roleRepository.getOne(id);
	}

	@Override
	@Transactional
	public Role postRole(Role user) {
		
		Role r = roleRepository.save(user);
		roleRepository.flush();
		
		return r;
	}

	@Override
	public Role updateRole(Long id, Role user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role deleteRoleById(Long id) {
		
		Role r = roleRepository.getOne(id);
		roleRepository.delete(r);
		roleRepository.flush();
		return r;
	}

	@Override
	public Role deleteRoleByType(String type) {
		
		Role r = roleRepository.findByType(type);
		roleRepository.delete(r);
		roleRepository.flush();
		return r;
	}

}
