package com.netflix.project.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.netflix.project.security.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByName(String name);
	
}
