package com.example.movieAI.film_management;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);

    @Autowired
    private MovieRepository movieRepository;

    public List<MovieEntity> findAllMovies() {
        try {
            return movieRepository.findAllMovies();
        } catch (DataAccessException ex) {
            logger.error("Error while fetching all movies from the database", ex);

            return new ArrayList<>();
        }
    }

    public List<MovieEntity> findCurrentMovies() {
        try {
            return movieRepository.findCurrentMovies();
        } catch (DataAccessException ex) {
            logger.error("Error while fetching current movies from the database", ex);

            return new ArrayList<>();
        }
    }

    public List<MovieEntity> findUpcomingMovies() {
        try {
            return movieRepository.findUpcomingMovies();
        } catch (DataAccessException ex) {
            logger.error("Error while fetching upcoming movies from the database", ex);

            return new ArrayList<>();
        }
    }

    public void deleteMovieById(Long id) {
        try {
            movieRepository.deleteMovieById(id);
        } catch (DataAccessException ex) {
            logger.error("Error while deleting movie with ID " + id, ex);

            throw ex;
        }
    }

    public void updateMovie(
            Long id,
            String name,
            String languages,
            String description,
            int runningTime,
            String releaseDate,
            float rating,
            int isCurrent
    ) {
        try {
            movieRepository.updateMovie(id, name, languages, description, runningTime, releaseDate, rating, isCurrent);
        } catch (DataAccessException ex) {
            logger.error("Error while updating movie with ID " + id, ex);

            throw ex;
        }
    }

    public void insertMovie(
            String name,
            String languages,
            String description,
            int runningTime,
            String releaseDate,
            float rating,
            String trailer,
            int isCurrent,
            String image
    ) {
        try {
            movieRepository.insertMovie(name, languages, description, runningTime, releaseDate, rating, trailer, isCurrent, image);
        } catch (DataAccessException ex) {
            logger.error("Error while inserting a new movie", ex);
            throw ex;
        }
    }
}
