package com.example.movieAI.film_management;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MovieRepository extends CrudRepository<MovieEntity, Long> {
    @Query("SELECT " +
            "m.id, " +
            "m.name AS title, " +
            "m.image AS bannerSrc, " +
            "m.rating, " +
            "m.runningTime AS runningTime, " +
            "m.trailer, " +
            "m.languages, " +
            "m.releaseDate, " +
            "m.description, " +
            "m.isCurrent, " +
            "GROUP_CONCAT(DISTINCT a.name SEPARATOR ', ') AS director, " +
            "GROUP_CONCAT(DISTINCT ac.name SEPARATOR ', ') AS actor, " +
            "GROUP_CONCAT(DISTINCT t.name SEPARATOR ', ') AS tag " +
            "FROM MovieEntity m " +
            "LEFT JOIN m.directors ma " +
            "LEFT JOIN ma.director a " +
            "LEFT JOIN m.actors mac " +
            "LEFT JOIN mac.actor ac " +
            "LEFT JOIN m.tags mt " +
            "LEFT JOIN mt.tag t " +
            "GROUP BY m.id, m.name")
    List<Object[]> findAllMovies();

    @Query("SELECT " +
            "m.id, " +
            "m.name AS title, " +
            "m.image AS bannerSrc, " +
            "m.rating, " +
            "m.runningTime AS runningTime, " +
            "m.trailer, " +
            "m.languages, " +
            "m.releaseDate, " +
            "m.description, " +
            "m.isCurrent, " +
            "GROUP_CONCAT(DISTINCT a.name SEPARATOR ', ') AS director, " +
            "GROUP_CONCAT(DISTINCT ac.name SEPARATOR ', ') AS actor, " +
            "GROUP_CONCAT(DISTINCT t.name SEPARATOR ', ') AS tag " +
            "FROM MovieEntity m " +
            "LEFT JOIN m.directors ma " +
            "LEFT JOIN ma.director a " +
            "LEFT JOIN m.actors mac " +
            "LEFT JOIN mac.actor ac " +
            "LEFT JOIN m.tags mt " +
            "LEFT JOIN mt.tag t " +
            "WHERE m.is_current = 1 "+
            "GROUP BY m.id, m.name")
    List<Object[]> findCurrentMovies();

    @Query("SELECT " +
            "m.id, " +
            "m.name AS title, " +
            "m.image AS bannerSrc, " +
            "m.rating, " +
            "m.runningTime AS runningTime, " +
            "m.trailer, " +
            "m.languages, " +
            "m.releaseDate, " +
            "m.description, " +
            "m.isCurrent, " +
            "GROUP_CONCAT(DISTINCT a.name SEPARATOR ', ') AS director, " +
            "GROUP_CONCAT(DISTINCT ac.name SEPARATOR ', ') AS actor, " +
            "GROUP_CONCAT(DISTINCT t.name SEPARATOR ', ') AS tag " +
            "FROM MovieEntity m " +
            "LEFT JOIN m.directors ma " +
            "LEFT JOIN ma.director a " +
            "LEFT JOIN m.actors mac " +
            "LEFT JOIN mac.actor ac " +
            "LEFT JOIN m.tags mt " +
            "LEFT JOIN mt.tag t " +
            "WHERE m.is_current = 0 "+
            "GROUP BY m.id, m.name")
    List<Object[]> findUpcomingMovies();

    @Transactional
    @Modifying
    @Query("DELETE FROM Movie m WHERE m.id = :id")
    void deleteMovieById(@Param("id") Long id);


    @Modifying
    @Query("UPDATE Movie m SET " +
            "m.name = :name, " +
            "m.languages = :languages, " +
            "m.description = :description, " +
            "m.running_time = :runningTime, " +
            "m.release_date = :releaseDate, " +
            "m.rating = :rating, " +
            "m.is_current = :isCurrent " +
            "WHERE m.id = :id")
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
    @Query("INSERT INTO Movie (name, languages, description, running_time, release_date, rating, trailer, is_current, image) " +
            "VALUES (:name, :languages, :description, :runningTime, :releaseDate, :rating, :trailer, :isCurrent, :image)")
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


}
