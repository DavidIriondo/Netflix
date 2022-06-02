package com.netflix.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.netflix.project.entities.TvShow;

@Repository
public interface TvShowRepository extends JpaRepository<TvShow, Long> {

	List<TvShow> findByCategoryId(Long categoryId); 
}
