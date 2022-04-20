package com.everis.d4i.tutorial.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.d4i.tutorial.security.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByType(String type);
}
