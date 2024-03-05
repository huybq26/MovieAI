package com.example.movieAI.booking;

import com.example.movieAI.film_management.MovieEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.*;

@Repository
public interface TimeslotRepository extends CrudRepository<TimeslotEntity, Long> {

}