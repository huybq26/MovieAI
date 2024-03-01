package com.example.movieAI.film_management;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<MovieEntity, Long> {
}
