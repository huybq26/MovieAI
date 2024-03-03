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

    // method to get the id from an author name

    // method to get the id from an actor name

    // method to get the id from an tag name

    // method to add new author

    // method to add new actor

    // method to add new tag


}
