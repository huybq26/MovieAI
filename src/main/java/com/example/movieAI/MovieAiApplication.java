package com.example.movieAI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.movieAI.film_management")
public class MovieAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieAiApplication.class, args);
	}

}
