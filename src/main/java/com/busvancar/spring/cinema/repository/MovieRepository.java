package com.busvancar.spring.cinema.repository;

import java.time.LocalDate;
import java.util.List;

import com.busvancar.spring.cinema.model.Movie;
import com.busvancar.spring.cinema.model.User;



public interface MovieRepository {

	Movie insertMovie(Movie movie);
	List<Movie> listAllMovies();
	Movie getMovie(int movieId);
	Movie updateMovie(int id, Movie movie);
	void removeMovie(int id);
	Movie createMovie(Movie movie);
	
}
