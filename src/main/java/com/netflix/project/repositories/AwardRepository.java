package com.netflix.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.netflix.project.entities.Award;

@Repository
public interface AwardRepository extends JpaRepository<Award, Long> {

}
