package com.busvancar.spring.cinema.service;

import java.util.List;

import javax.validation.Valid;

import com.busvancar.spring.cinema.dto.MovieSessionDto;
import com.busvancar.spring.cinema.model.MovieSession;

public interface MovieSessionService {
	
	public Integer ROWS = 12;

	public List<MovieSession> getAllMovieSessions(Integer movieId);

	public MovieSessionDto getMovieSession(Integer id);

	public MovieSessionDto insertMovieSession(@Valid MovieSessionDto msDto);

	public void removeMovieSession(Integer id);

	public Integer getMovieSessionBasePrice(Integer id);

	//public Integer getPrice(Integer movieSessionBasePrice, Integer seat);

	public void updateMovieSessionAvailableSeats(Integer movieSession, Integer availableSeats);


}
