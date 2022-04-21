SELECT DISTINCT netflix.tv_shows.ID as tvShowId, netflix.tv_shows.NAME as tvShow , netflix.seasons.ID as season, netflix.chapters.ID as chapter 
FROM netflix.chapters, netflix.tv_shows, netflix.seasons, netflix.actors , netflix.participates
WHERE netflix.tv_shows.ID = netflix.seasons.TV_SHOW_ID
AND netflix.seasons.ID = netflix.chapters.SEASON_ID
AND netflix.chapters.ID = netflix.participates.chapters_id
AND netflix.participates.actor_id = 1;
