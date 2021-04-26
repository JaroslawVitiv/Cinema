package com.busvancar.spring.cinema.service;

import java.util.List;

import javax.validation.Valid;

import com.busvancar.spring.cinema.dto.MovieSessionDto;
import com.busvancar.spring.cinema.model.MovieSession;

public interface MovieSessionService {
	
	int ROWS = 12;

	List<MovieSession> getAllMovieSessions(int movieId);

	MovieSessionDto getMovieSession(int sessionID);

	MovieSessionDto insertMovieSession(@Valid MovieSessionDto msDto);

	void removeMovieSession(int sessionID);

	int getMovieSessionBasePrice(int sessionID);

	double getPrice(int movieSessionBasePrice, int seat);

	void updateMovieSessionAvailableSeats(int movieSession, int availableSeats);


}
