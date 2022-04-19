package com.everis.d4i.tutorial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.d4i.tutorial.entities.Award;

@Repository
public interface AwardRepository extends JpaRepository<Award, Long> {

}
