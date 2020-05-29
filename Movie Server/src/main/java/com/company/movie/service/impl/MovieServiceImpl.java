package com.company.movie.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.movie.entity.Movie;
import com.company.movie.entity.repository.MovieRepository;
import com.company.movie.exception.AppError;
import com.company.movie.exception.AppException;
import com.company.movie.service.MovieService;
import com.company.movie.util.JWTUtil;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieRepository movieRepository;

	public List<Movie> getMovies(HttpServletRequest request) {
		List<Movie> movies = null;
		try {
			validateAuthToken(request);
			movies = movieRepository.findAll();
			if (movies == null || movies.isEmpty()) {
				throw new AppException(AppError.MOVIES_DOES_NOT_EXIST);
			}
		} catch (AppException e) {
			throw e;
		} catch (Exception e) {
			throw new AppException(AppError.MOVIES_FETCH_FAILED);
		}
		return movies;

	}

	public Movie getMovieById(long id, HttpServletRequest request) {
		validateAuthToken(request);
		return getMovieById(id);

	}

	public Movie AddMovie(Movie movieObj, HttpServletRequest request) {
		Movie movie = null;
		try {
			validateAuthToken(request);
			movie = movieRepository.save(movieObj);
		} catch (Exception e) {
			throw new AppException(AppError.MOVIE_ADD_FAILED, "unable to add movie");
		}
		return movie;
	}

	public Movie updateMovieById(long id, Movie movie, HttpServletRequest request) {
		Movie updatedMovie = null;
		try {
			validateAuthToken(request);
			updatedMovie = getMovieById(id);
			updatedMovie.setName(movie.getName());
			updatedMovie.setDirector(movie.getDirector());
			movieRepository.save(updatedMovie);
		} catch (Exception e) {
			throw new AppException(AppError.MOVIE_UPDATE_FAILED, "unable to update movie");
		}
		return updatedMovie;
	}

	@Transactional
	public Boolean deleteMovieById(long id, HttpServletRequest request) {
		Boolean isDeleted = false;
		try {
			validateAuthToken(request);
			getMovieById(id);
			movieRepository.deleteById(id);
			isDeleted = true;
		} catch (Exception e) {
			throw new AppException(AppError.MOVIE_DELETE_FAILED, "unable to delete movie");
		}
		return isDeleted;
	}

	private Movie getMovieById(long id) {
		Movie movie = null;
		try {
			movie = movieRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new AppException(AppError.MOVIE_DOES_NOT_EXIST);
		} catch (Exception e) {
			throw new AppException(AppError.MOVIE_FETCH_FAILED);
		}
		return movie;
	}

	private void validateAuthToken(HttpServletRequest request) {
		try {
			String token = request.getHeader("X-AUTH-TOKEN");
			if (token == null)
				throwUnAuthenticated();
			byte[] apiKey = "movie.secure.token".getBytes();
			Claims claims = JWTUtil.parseToken(token, apiKey);
			String userName = claims.get("userName").toString();
			if (userName == null)
				throwUnAuthenticated();
		} catch (NullPointerException e) {
			throwUnAuthenticated();
		} catch (AppException e) {
			throw e;
		} catch (Exception e) {
			throw new AppException(AppError.AUTH_TOKEN_VALIDATION_FAILED);
		}
	}

	private void throwUnAuthenticated() {
		throw new AppException(AppError.AUTH_TOKEN_INVALID);
	}
}
