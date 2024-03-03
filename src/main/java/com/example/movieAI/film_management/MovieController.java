package com.example.movieAI.film_management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/all")
    public ResponseEntity<List<MovieEntity>> getAllMovies() {
        List<MovieEntity> movies = movieService.findAllMovies();

        movies.forEach(movie -> System.out.println(movie.getName() +
                " - Actors: " + movie.getActor() +
                " - Directors: " + movie.getDirector() +
                " - Tags: " + movie.getTag()));

        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/current")
    public ResponseEntity<List<MovieEntity>> getCurrentMovies() {
        List<MovieEntity> currentMovies = movieService.findCurrentMovies();
        currentMovies.forEach(movie -> System.out.println(movie.getName() +
                " - Actors: " + movie.getActor() +
                " - Directors: " + movie.getDirector() +
                " - Tags: " + movie.getTag()));
        return new ResponseEntity<>(currentMovies, HttpStatus.OK);
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<MovieEntity>> getUpcomingMovies() {
        List<MovieEntity> upcomingMovies = movieService.findUpcomingMovies();
        upcomingMovies.forEach(movie -> System.out.println(movie.getName() +
                " - Actors: " + movie.getActor() +
                " - Directors: " + movie.getDirector() +
                " - Tags: " + movie.getTag()));
        return new ResponseEntity<>(upcomingMovies, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovieById(@PathVariable Long id) {
        movieService.deleteMovieById(id);
        return new ResponseEntity<>("Movie with ID " + id + " deleted successfully.", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMovie(
            @PathVariable Long id,
            @RequestBody MovieEntity movieRequestDto
    ) {
        movieService.updateMovie(
                id,
                movieRequestDto.getName(),
                movieRequestDto.getLanguages(),
                movieRequestDto.getDescription(),
                movieRequestDto.getRunning_time(),
                movieRequestDto.getRelease_date(),
                movieRequestDto.getRating(),
                movieRequestDto.getIs_current()
        );
        return new ResponseEntity<>("Movie with ID " + id + " updated successfully.", HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> insertMovie(@RequestBody MovieEntity movieRequestDto) {
        movieService.insertMovie(
                movieRequestDto.getName(),
                movieRequestDto.getLanguages(),
                movieRequestDto.getDescription(),
                movieRequestDto.getRunning_time(),
                movieRequestDto.getRelease_date(),
                movieRequestDto.getRating(),
                movieRequestDto.getTrailer(),
                movieRequestDto.getIs_current(),
                movieRequestDto.getImage()
        );
        return new ResponseEntity<>("New movie added successfully.", HttpStatus.CREATED);
    }
}
