package com.busvancar.spring.cinema.repository.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.busvancar.spring.cinema.model.Movie;
import com.busvancar.spring.cinema.model.User;
import com.busvancar.spring.cinema.repository.MovieRepository;
import com.busvancar.spring.cinema.repository.UserRepository;

@Component
public class MovieRepositoryImpl implements MovieRepository{
	
	private List<Movie> list = new ArrayList<>();
	
		
	@Override
	public List<Movie> listAllMovies() {
		return list;
	}
	
	@Override
	public Movie getMovie(int movieId) {
		return list.stream()
				.filter(movie -> (movie.getId() == movieId))
				.findFirst()
				.orElseThrow(RuntimeException::new);
	}

	@Override
	public Movie insertMovie(Movie movie) {
		list.add(movie);
		return movie;
	}

	
	@Override
	public Movie updateMovie(int id, Movie movie) {
		boolean isDeleted = list.removeIf(mv -> (mv.getId() == id));
		if(isDeleted) {
			list.add(movie);
		}else {
			throw new RuntimeException("Movie does not exist!");
		}
		
		return movie;
	}
	
	@Override
	public void removeMovie(int id) {
		list.removeIf(movie -> (movie.getId() == id));
	}

	@Override
	public Movie createMovie(Movie movie) {
		list.add(movie);
		return movie;
	}
		
}
