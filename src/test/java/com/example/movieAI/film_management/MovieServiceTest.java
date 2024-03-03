package com.example.movieAI.film_management;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {
    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    public void testFindAllMovies() {
        List<MovieEntity> mockMovies = new ArrayList<>();
        Mockito.when(movieRepository.findAllMovies()).thenReturn(mockMovies);

        List<MovieEntity> result = movieService.findAllMovies();

        assertEquals(mockMovies, result);
    }

    @Test
    public void testFindAllMoviesWithDatabaseError() {
        Mockito.when(movieRepository.findAllMovies()).thenThrow(new DataAccessException("Database error") {
        });
        List<MovieEntity> result = movieService.findAllMovies();
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testFindCurrentMovies() {
        List<MovieEntity> mockCurrentMovies = new ArrayList<>();
        Mockito.when(movieRepository.findCurrentMovies()).thenReturn(mockCurrentMovies);

        List<MovieEntity> result = movieService.findCurrentMovies();

        assertEquals(mockCurrentMovies, result);
    }

    @Test
    public void testFindUpcomingMovies() {
        List<MovieEntity> mockUpcomingMovies = new ArrayList<>();
        Mockito.when(movieRepository.findUpcomingMovies()).thenReturn(mockUpcomingMovies);

        List<MovieEntity> result = movieService.findUpcomingMovies();

        assertEquals(mockUpcomingMovies, result);
    }

    @Test
    public void testDeleteMovieById() {
        Long movieId = 1L;

        movieService.deleteMovieById(movieId);

        verify(movieRepository).deleteMovieById(movieId);
    }

    @Test
    public void testUpdateMovie() {
        Long movieId = 1L;
        String name = "Updated Movie";
        String languages = "English";
        String description = "Updated Description";
        int runningTime = 120;
        String releaseDate = "2022-03-01";
        float rating = 4.5f;
        int isCurrent = 1;

        movieService.updateMovie(movieId, name, languages, description, runningTime, releaseDate, rating, isCurrent);

        verify(movieRepository).updateMovie(movieId, name, languages, description, runningTime, releaseDate, rating, isCurrent);
    }

    @Test
    public void testUpdateMovieWithInvalidId() {

        Long invalidMovieId = 999L;
        String name = "Updated Movie";
        String languages = "English";
        String description = "Updated Description";
        int runningTime = 120;
        String releaseDate = "2022-03-01";
        float rating = 4.5f;
        int isCurrent = 1;

        doThrow(new EmptyResultDataAccessException(1)).when(movieRepository)
                .updateMovie(invalidMovieId, name, languages, description, runningTime, releaseDate, rating, isCurrent);


        assertThrows(EmptyResultDataAccessException.class, () ->
                movieService.updateMovie(invalidMovieId, name, languages, description, runningTime, releaseDate, rating, isCurrent)
        );
    }

    @Test
    public void testDeleteMovieByIdWithInvalidId() {

        Long invalidMovieId = 999L;

        doThrow(new EmptyResultDataAccessException(1)).when(movieRepository).deleteMovieById(invalidMovieId);


        assertThrows(EmptyResultDataAccessException.class, () ->
                movieService.deleteMovieById(invalidMovieId)
        );


        verify(movieRepository).deleteMovieById(invalidMovieId);
    }

    @Test
    public void testDeleteMovieByIdWithValidId() {

        Long validMovieId = 1L;


        assertDoesNotThrow(() ->
                movieService.deleteMovieById(validMovieId)
        );


        verify(movieRepository).deleteMovieById(validMovieId);
    }

    @Test
    public void testInsertMovieWithDatabaseError() {

        doThrow(new DataAccessException("Database error") {}).when(movieRepository)
                .insertMovie("Test Movie", "English", "Description", 120, "2022-03-01", 4.5f, "Trailer", 1, "Image");


        assertThrows(DataAccessException.class, () ->
                movieService.insertMovie("Test Movie", "English", "Description", 120, "2022-03-01", 4.5f, "Trailer", 1, "Image", "BuiQHuy", "BuiQHuy,Harry,Bui", "Comedy,Adventure")
        );
    }
}
