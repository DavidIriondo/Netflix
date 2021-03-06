package com.netflix.project.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.netflix.project.security.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByType(String type);
}
