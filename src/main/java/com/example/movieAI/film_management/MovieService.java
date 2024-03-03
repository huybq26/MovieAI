package com.example.movieAI.film_management;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
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
            String image,
            String directorNamesAsString,
            String actorNamesAsString,
            String tagNamesAsString
    ) {
        List<String> directorNames = Arrays.asList(directorNamesAsString.split(","));
        List<String> actorNames = Arrays.asList(actorNamesAsString.split(","));
        List<String> tagNames = Arrays.asList(tagNamesAsString.split(","));
        try {
            movieRepository.insertMovie(name, languages, description, runningTime, releaseDate, rating, trailer, isCurrent, image);

            Long movieId = movieRepository.findMovieIdByName(name);

            for (String directorName : directorNames) {
                Long directorId = movieRepository.findDirectorIdByName(directorName);
                if (directorId == null) {
                    movieRepository.insertDirector(directorName);
                    directorId = movieRepository.findDirectorIdByName(directorName);
                }
                movieRepository.linkDirectorWithMovie(movieId, directorId);
            }

            for (String actorName : actorNames) {
                Long actorId = movieRepository.findActorIdByName(actorName);
                if (actorId == null) {
                    movieRepository.insertActor(actorName);
                    actorId = movieRepository.findActorIdByName(actorName);
                }
                movieRepository.linkActorWithMovie(movieId, actorId);
            }

            for (String tagName : tagNames) {
                Long tagId = movieRepository.findTagIdByName(tagName);
                if (tagId == null) {
                    movieRepository.insertTag(tagName);
                    tagId = movieRepository.findTagIdByName(tagName);
                }
                movieRepository.linkTagWithMovie(movieId, tagId);
            }

        } catch (DataAccessException ex) {
            logger.error("Error while inserting a new movie", ex);
            throw ex;
        }
    }


    public Long findDirectorIdByName(String directorName) {
        try {
            return movieRepository.findDirectorIdByName(directorName);
        } catch (DataAccessException ex) {
            logger.error("Error while finding director ID by name: " + directorName, ex);
            return null;
        }
    }

    public Long findActorIdByName(String actorName) {
        try {
            return movieRepository.findActorIdByName(actorName);
        } catch (DataAccessException ex) {
            logger.error("Error while finding actor ID by name: " + actorName, ex);
            return null;
        }
    }

    public Long findTagIdByName(String tagName) {
        try {
            return movieRepository.findTagIdByName(tagName);
        } catch (DataAccessException ex) {
            logger.error("Error while finding tag ID by name: " + tagName, ex);
            return null;
        }
    }

    @Transactional
    public void insertDirector(String directorName) {
        try {
            movieRepository.insertDirector(directorName);
        } catch (DataAccessException ex) {
            logger.error("Error while inserting director: " + directorName, ex);
            throw ex;
        }
    }

    @Transactional
    public void insertActor(String actorName) {
        try {
            movieRepository.insertActor(actorName);
        } catch (DataAccessException ex) {
            logger.error("Error while inserting actor: " + actorName, ex);
            throw ex;
        }
    }

    @Transactional
    public void insertTag(String tagName) {
        try {
            movieRepository.insertTag(tagName);
        } catch (DataAccessException ex) {
            logger.error("Error while inserting tag: " + tagName, ex);
            throw ex;
        }
    }

    @Transactional
    public void linkDirectorWithMovie(Long movieId, Long directorId) {
        try {
            movieRepository.linkDirectorWithMovie(movieId, directorId);
        } catch (DataAccessException ex) {
            logger.error("Error while linking director with movie (Movie ID: " + movieId + ", Director ID: " + directorId + ")", ex);
            throw ex;
        }
    }

    @Transactional
    public void linkActorWithMovie(Long movieId, Long actorId) {
        try {
            movieRepository.linkActorWithMovie(movieId, actorId);
        } catch (DataAccessException ex) {
            logger.error("Error while linking actor with movie (Movie ID: " + movieId + ", Actor ID: " + actorId + ")", ex);
            throw ex;
        }
    }

    @Transactional
    public void linkTagWithMovie(Long movieId, Long tagId) {
        try {
            movieRepository.linkTagWithMovie(movieId, tagId);
        } catch (DataAccessException ex) {
            logger.error("Error while linking tag with movie (Movie ID: " + movieId + ", Tag ID: " + tagId + ")", ex);
            throw ex;
        }
    }

    @Transactional
    public void updateTrailer(Long id, String trailer) {
        try {
            movieRepository.updateTrailer(id, trailer);
        } catch (DataAccessException ex) {
            logger.error("Error while updating trailer for movie with ID: " + id, ex);
            throw ex;
        }
    }

    @Transactional
    public void updateImage(Long id, String image) {
        try {
            movieRepository.updateImage(id, image);
        } catch (DataAccessException ex) {
            logger.error("Error while updating image for movie with ID: " + id, ex);
            throw ex;
        }
    }

    public List<TriviaEntity> findAllTrivia() {
        try {
            return movieRepository.findAllTrivia();
        } catch (DataAccessException ex) {
            logger.error("Error while fetching all trivia from the database", ex);
            return new ArrayList<>();
        }
    }
}
