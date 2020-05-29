package com.company.movie;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.company.movie.entity.Movie;
import com.company.movie.entity.repository.MovieRepository;

@SpringBootApplication
@EnableJpaRepositories()
public class MovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoData(MovieRepository movieRepository) {
		return args -> {
			List<Movie> movies = new ArrayList<Movie>();
			movies.add(new Movie("Avengers: Endgame", "Anthony Russo"));
			movies.add(new Movie("Dark Phoenix ", "Simon Kinberg"));
			movies.add(new Movie("The Secret Life of Pets 2", " Chris Renaud"));
			movieRepository.saveAll(movies);
		};
	}
}
