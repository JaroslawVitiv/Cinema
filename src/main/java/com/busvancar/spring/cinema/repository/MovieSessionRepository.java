package com.busvancar.spring.cinema.repository;

import java.util.List;

import com.busvancar.spring.cinema.model.MovieSession;

public interface MovieSessionRepository {
	
	final int ROWS = 12;

	List<MovieSession> getAllMovieSessions(int movieId);

	MovieSession insertMovieSession(MovieSession ms);

	void removeMovieSession(int movieId);

	MovieSession getMovieSession(int sessionID);

	int getMovieSessionBasePrice(int sessionID);

	double getPrice(int movieSessionBasePrice, int seat);

	void updateMovieSessionAvailableSeats(int movieSession, int availableSeats);

}
