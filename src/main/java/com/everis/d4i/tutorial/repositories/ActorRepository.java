package com.everis.d4i.tutorial.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.json.ActorChapterRest;
import com.everis.d4i.tutorial.projections.ActorChapterProjection;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long>{

	@Query(value = "SELECT DISTINCT netflix.tv_shows.ID as tvShowId, netflix.tv_shows.NAME as tvShow , netflix.seasons.ID as season, netflix.chapters.ID as chapter \r\n"
			+ "FROM netflix.chapters, netflix.tv_shows, netflix.seasons, netflix.actors , netflix.participates\r\n"
			+ "WHERE netflix.tv_shows.ID = netflix.seasons.TV_SHOW_ID\r\n"
			+ "AND netflix.seasons.ID = netflix.chapters.SEASON_ID\r\n"
			+ "AND netflix.chapters.ID = netflix.participates.chapters_id\r\n"
			+ "AND netflix.participates.actor_id = ?;", nativeQuery = true)
	List<ActorChapterProjection> getActorChapters(Long id);
}
