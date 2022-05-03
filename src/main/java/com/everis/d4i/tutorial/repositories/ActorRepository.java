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

	@Query(value = "SELECT DISTINCT tv_shows.ID as tvShowId, tv_shows.NAME as tvShow , seasons.ID as season, chapters.ID as chapter \r\n"
			+ "FROM chapters, tv_shows, seasons, actors , participates\r\n"
			+ "WHERE tv_shows.ID = seasons.TV_SHOW_ID\r\n"
			+ "AND seasons.ID = chapters.SEASON_ID\r\n"
			+ "AND chapters.ID = participates.chapters_id\r\n"
			+ "AND participates.actor_id = ?;", nativeQuery = true)
	List<ActorChapterProjection> getActorChapters(Long id);
}
