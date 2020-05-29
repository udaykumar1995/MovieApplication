package com.company.movie.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.movie.entity.Movie;
import com.company.movie.service.MovieService;

@RestController
@RequestMapping(value = "movie")
@CrossOrigin(origins = {"http://ec2-18-219-121-39.us-east-2.compute.amazonaws.com:4200", "http://movieapplication.s3-website.us-east-2.amazonaws.com", "http://localhost:4200", "http://localhost:3000"})
public class MovieController {

	@Autowired
	MovieService movieService;

	@GetMapping()
	public ResponseEntity<List<Movie>> getMovies(HttpServletRequest request) throws Exception {
		return ResponseEntity.ok().body(movieService.getMovies(request));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Movie> getMovie(@PathVariable long id, HttpServletRequest request) {
		return ResponseEntity.ok().body(movieService.getMovieById(id, request));
	}

	@PostMapping()
	public ResponseEntity<Movie> addMovie(@RequestBody Movie movie, HttpServletRequest request) {
		return ResponseEntity.ok().body(movieService.AddMovie(movie, request));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie, @PathVariable long id,
			HttpServletRequest request) {
		return ResponseEntity.ok().body(movieService.updateMovieById(id, movie, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteMovie(@PathVariable long id, HttpServletRequest request) {
		return ResponseEntity.ok().body(movieService.deleteMovieById(id, request));
	}
}
