package com.busvancar.spring.cinema.service;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.busvancar.spring.cinema.dto.MovieDto;
import com.busvancar.spring.cinema.dto.UserDto;
import com.busvancar.spring.cinema.model.Movie;

public interface MovieService {

	List<Movie> listAllMovies();

	MovieDto getMovie(int id);

	MovieDto insertMovie(MovieDto movieDto);
	
	MovieDto createMovie(MovieDto movieDto);


	MovieDto updateMovie(int id, MovieDto movieDto);

	void removeMovie(int id);


}
