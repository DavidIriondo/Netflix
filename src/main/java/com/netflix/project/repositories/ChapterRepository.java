package com.netflix.project.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.netflix.project.entities.Chapter;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {

	List<Chapter> findBySeasonTvShowIdAndSeasonNumber(Long tvShowId, short seasonNumber);

	Optional<Chapter> findBySeasonTvShowIdAndSeasonNumberAndNumber(Long tvShowId, short seasonNumber,
			short chapterNumber);

}
