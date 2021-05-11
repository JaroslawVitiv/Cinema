package com.busvancar.spring.cinema.service;

import java.util.List;

import com.busvancar.spring.cinema.dto.MovieDto;
import com.busvancar.spring.cinema.model.Movie;

public interface MovieService {

	public List<Movie> listAllMovies();

	public MovieDto getMovie(Integer id);

	public MovieDto insertMovie(MovieDto movieDto);
	
	public void removeMovie(Integer id);

	public MovieDto updateMovie(Integer id, MovieDto movieDto);

	public MovieDto saveOrUpdate(Integer id, MovieDto movieDto);


}
