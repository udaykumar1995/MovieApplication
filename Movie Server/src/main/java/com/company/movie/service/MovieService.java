package com.company.movie.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.company.movie.entity.Movie;

public interface MovieService {

	public List<Movie> getMovies(HttpServletRequest request) throws Exception;

	public Movie getMovieById(long id, HttpServletRequest request);

	public Movie AddMovie(Movie movieObj, HttpServletRequest request);

	public Movie updateMovieById(long id, Movie movie, HttpServletRequest request);

	public Boolean deleteMovieById(long id, HttpServletRequest request);
}
