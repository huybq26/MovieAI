package com.example.movieAI.film_management;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<MovieEntity, Long> {
    @Query(value = "SELECT DISTINCT m FROM MovieEntity m " +
            "LEFT JOIN FETCH m.directors " +
            "LEFT JOIN FETCH m.actors " +
            "LEFT JOIN FETCH m.tags")
    List<MovieEntity> findAllMovies();

    @Query(value = "SELECT DISTINCT m FROM MovieEntity m " +
            "LEFT JOIN FETCH m.directors " +
            "LEFT JOIN FETCH m.actors " +
            "LEFT JOIN FETCH m.tags " +
            "WHERE m.is_current = 1 ")
    List<MovieEntity> findCurrentMovies();


    @Query(value = "SELECT DISTINCT m FROM MovieEntity m " +
            "LEFT JOIN FETCH m.directors " +
            "LEFT JOIN FETCH m.actors " +
            "LEFT JOIN FETCH m.tags " +
            "WHERE m.is_current = 0")
    List<MovieEntity> findUpcomingMovies();

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM movie m WHERE m.id = :id", nativeQuery = true)
    void deleteMovieById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE movie m SET " +
            "m.name = :name, " +
            "m.languages = :languages, " +
            "m.description = :description, " +
            "m.running_time = :runningTime, " +
            "m.release_date = :releaseDate, " +
            "m.rating = :rating, " +
            "m.is_current = :isCurrent " +
            "WHERE m.id = :id", nativeQuery = true)
    void updateMovie(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("languages") String languages,
            @Param("description") String description,
            @Param("runningTime") int runningTime,
            @Param("releaseDate") String releaseDate,
            @Param("rating") float rating,
            @Param("isCurrent") int isCurrent
    );

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO movie (name, languages, description, running_time, release_date, rating, trailer, is_current, image) " +
            "VALUES (:name, :languages, :description, :runningTime, :releaseDate, :rating, :trailer, :isCurrent, :image)", nativeQuery = true)
    void insertMovie(
            @Param("name") String name,
            @Param("languages") String languages,
            @Param("description") String description,
            @Param("runningTime") int runningTime,
            @Param("releaseDate") String releaseDate,
            @Param("rating") float rating,
            @Param("trailer") String trailer,
            @Param("isCurrent") int isCurrent,
            @Param("image") String image
    );

    // method to get the id from an director name
    @Query(value = "SELECT a.id FROM DirectorEntity a WHERE a.name = :directorName")
    Long findDirectorIdByName(@Param("directorName") String directorName);

    // method to get the id from an actor name
    @Query(value = "SELECT ac.id FROM ActorEntity ac WHERE ac.name = :actorName")
    Long findActorIdByName(@Param("actorName") String actorName);

    // method to get the id from an tag name
    @Query(value = "SELECT t.id FROM TagEntity t WHERE t.name = :tagName")
    Long findTagIdByName(@Param("tagName") String tagName);

    // method to add new director
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO director (name) VALUES (:directorName)", nativeQuery = true)
    void insertDirector(@Param("directorName") String directorName);

    // method to add new actor
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO actor (name) VALUES (:actorName)", nativeQuery = true)
    void insertActor(@Param("actorName") String actorName);

    // method to add new tag
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO tag (name) VALUES (:tagName)", nativeQuery = true)
    void insertTag(@Param("tagName") String tagName);


    // method to link a director id with a movie id
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO movie_director (movie_id, director_id) VALUES (:movieId, :directorId)", nativeQuery = true)
    void linkDirectorWithMovie(@Param("movieId") Long movieId, @Param("directorId") Long directorId);


    // method to link an actor id with a movie id
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO movie_actor (movie_id, actor_id) VALUES (:movieId, :actorId)", nativeQuery = true)
    void linkActorWithMovie(@Param("movieId") Long movieId, @Param("actorId") Long actorId);


    // method to link a tag id with a movie id
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO movie_tag (movie_id, tag_id) VALUES (:movieId, :tagId)", nativeQuery = true)
    void linkTagWithMovie(@Param("movieId") Long movieId, @Param("tagId") Long tagId);


    // method to modify trailer attribute from movie id
    @Transactional
    @Modifying
    @Query(value = "UPDATE movie SET trailer = :trailer WHERE id = :id", nativeQuery = true)
    void updateTrailer(@Param("id") Long id, @Param("trailer") String trailer);


    // method to modify image attribute from movie id
    @Transactional
    @Modifying
    @Query(value = "UPDATE movie SET image = :image WHERE id = :id", nativeQuery = true)
    void updateImage(@Param("id") Long id, @Param("image") String image);


    // method to get all trivia
    @Query(value = "SELECT t FROM TriviaEntity t")
    List<TriviaEntity> findAllTrivia();

    // method to find movie id by name
    @Query(value = "SELECT m.id FROM MovieEntity m WHERE m.name = :movieName")
    Long findMovieIdByName(@Param("movieName") String movieName);


}
