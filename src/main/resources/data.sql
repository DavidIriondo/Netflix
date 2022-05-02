/*SECURITY USERS*/
INSERT INTO user(id, name, password) VALUES(1, 'user', '$2a$10$qtJ.PqA5bxVmCrOoi5Pfc.fkz4GJpPGDM/5URGtFj6gcvO6Wa3Fqy');
INSERT INTO user(id, name, password) VALUES(2, 'admin', '$2a$10$vzGv7XVOU68K8e3rt8L8ZuOufrMbpkM5qQ9AK9iffDb8BlLEABuZS');

/*SECURITY ROLES*/
INSERT INTO role(id, type) VALUES(1, 'USER');
INSERT INTO role(id, type) VALUES(2, 'ADMIN');

/*SECURITY ROLES-USERS RELATIONSHIP*/
INSERT INTO has_role(user_id, role_id) VALUES(1, 1);
INSERT INTO has_role(user_id, role_id) VALUES(2, 2);

/*ACTORS*/
INSERT INTO actors(id, age, name, surname) VALUES(1, 40, 'Robert', 'Downey Jr.');
INSERT INTO actors(id, age, name, surname) VALUES(2, 35, 'Scarlet', 'Johansson');
INSERT INTO actors(id, age, name, surname) VALUES(3, 45, 'Chris', ' Evans');
INSERT INTO actors(id, age, name, surname) VALUES(4, 55, 'Chris', 'Hemsworth');

/*TV SHOWS*/
INSERT INTO tv_shows(id, name, short_desc, long_desc, year, recommended_age, advertising) VALUES(1, 'Game of thrones', 'short desc', 'long desc', 2012, 18, NULL);
INSERT INTO tv_shows(id, name, short_desc, long_desc, year, recommended_age, advertising) VALUES(2, 'Breaking bad', 'short desc', 'long desc', 2015, 16, NULL);
INSERT INTO tv_shows(id, name, short_desc, long_desc, year, recommended_age, advertising) VALUES(3, 'The simpsons', 'short desc', 'long desc', 1990, 12, NULL);
INSERT INTO tv_shows(id, name, short_desc, long_desc, year, recommended_age, advertising) VALUES(4, 'Family guy', 'short desc', 'long desc', 2008, 18, NULL);

/*SEASONS*/
INSERT INTO seasons(id, number, name, tv_show_id) VALUES(1, 1, 'One', 1);
INSERT INTO seasons(id, number, name, tv_show_id) VALUES(2, 2, 'Two', 1);
INSERT INTO seasons(id, number, name, tv_show_id) VALUES(3, 1, 'One', 2);
INSERT INTO seasons(id, number, name, tv_show_id) VALUES(4, 1, 'One', 3);
INSERT INTO seasons(id, number, name, tv_show_id) VALUES(5, 1, 'One', 4);


/*CHAPTERS*/
INSERT INTO chapters(id, number, name, duration, season_id) VALUES(1, 1, 'Chapter 1', 20, 1);
INSERT INTO chapters(id, number, name, duration, season_id) VALUES(2, 2, 'Chapter 2', 20, 1);
INSERT INTO chapters(id, number, name, duration, season_id) VALUES(3, 1, 'Chapter 1', 20, 2);
INSERT INTO chapters(id, number, name, duration, season_id) VALUES(4, 1, 'Chapter 1', 20, 3);
INSERT INTO chapters(id, number, name, duration, season_id) VALUES(5, 2, 'Chapter 2', 20, 3);
INSERT INTO chapters(id, number, name, duration, season_id) VALUES(6, 1, 'Chapter 1', 20, 4);
INSERT INTO chapters(id, number, name, duration, season_id) VALUES(7, 2, 'Chapter 2', 20, 4);
INSERT INTO chapters(id, number, name, duration, season_id) VALUES(8, 3, 'Chapter 3', 20, 4);
INSERT INTO chapters(id, number, name, duration, season_id) VALUES(9, 1, 'Chapter 1', 20, 5);
INSERT INTO chapters(id, number, name, duration, season_id) VALUES(10, 2, 'Chapter 2', 20, 5);

/*CATEGORIES*/
INSERT INTO categories(id, name) VALUES(1, 'DRAMA');
INSERT INTO categories(id, name) VALUES(2, 'COMEDY');
INSERT INTO categories(id, name) VALUES(3, 'FAMILY');

/*TV SHOW CATEGORIES RELATIONSHIP*/
INSERT INTO tv_show_categories(tvshow_id, categories_id)VALUES(1, 1);
INSERT INTO tv_show_categories(tvshow_id, categories_id)VALUES(2, 1);
INSERT INTO tv_show_categories(tvshow_id, categories_id)VALUES(3, 3);
INSERT INTO tv_show_categories(tvshow_id, categories_id)VALUES(3, 2);
INSERT INTO tv_show_categories(tvshow_id, categories_id)VALUES(4, 2);

/*AWARDS*/
INSERT INTO awards(id, date, name, tv_show_id)VALUES(1, NULL, 'OSCARS', 1);
INSERT INTO awards(id, date, name, tv_show_id)VALUES(2, NULL, 'TV SHOW OF THE YEAR', 3);
INSERT INTO awards(id, date, name, tv_show_id)VALUES(3, NULL, 'SLAM', 2);


/*ACTOR AND CHAPTERS RELATIONSHIP*/
INSERT INTO participates(actor_id, chapters_id)VALUES(1, 1);
INSERT INTO participates(actor_id, chapters_id)VALUES(2, 1);
INSERT INTO participates(actor_id, chapters_id)VALUES(2, 5);
INSERT INTO participates(actor_id, chapters_id)VALUES(3, 4);
INSERT INTO participates(actor_id, chapters_id)VALUES(3, 2);
INSERT INTO participates(actor_id, chapters_id)VALUES(3, 8);
INSERT INTO participates(actor_id, chapters_id)VALUES(4, 8);
INSERT INTO participates(actor_id, chapters_id)VALUES(4, 2);




