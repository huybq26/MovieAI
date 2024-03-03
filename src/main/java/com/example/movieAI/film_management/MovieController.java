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
    public ResponseEntity<String> insertMovie(@RequestParam String name,
                                              @RequestParam String languages,
                                              @RequestParam String description,
                                              @RequestParam int running_time,
                                              @RequestParam String release_date,
                                              @RequestParam float rating,
                                              @RequestParam String trailer,
                                              @RequestParam int is_current,
                                              @RequestParam String image,
                                              @RequestParam String director,
                                              @RequestParam String actor,
                                              @RequestParam String tag) {
        movieService.insertMovie(
                name, languages, description, running_time, release_date, rating, trailer, is_current, image, director, actor, tag
        );
        return new ResponseEntity<>("New movie added successfully.", HttpStatus.CREATED);
    }

    @PatchMapping("/updateTrailer/{id}")
    public ResponseEntity<String> updateTrailer(@PathVariable Long id, @RequestParam String trailer) {
        movieService.updateTrailer(id, trailer);
        return ResponseEntity.ok("Trailer updated successfully.");
    }

    @PatchMapping("/updateImage/{id}")
    public ResponseEntity<String> updateImage(@PathVariable Long id, @RequestParam String image) {
        movieService.updateImage(id, image);
        return ResponseEntity.ok("Image updated successfully.");
    }

    @GetMapping("/allTrivia")
    public ResponseEntity<List<TriviaEntity>> findAllTrivia() {
        List<TriviaEntity> triviaList = movieService.findAllTrivia();
        return ResponseEntity.ok(triviaList);
    }

}
