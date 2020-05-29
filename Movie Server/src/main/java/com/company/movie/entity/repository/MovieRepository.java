package com.company.movie.entity.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.company.movie.entity.Movie;

public interface MovieRepository extends CrudRepository<Movie, Long> {
	
	List<Movie> findAll();
}
