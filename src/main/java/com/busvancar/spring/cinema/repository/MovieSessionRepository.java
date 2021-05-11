package com.busvancar.spring.cinema.repository;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.busvancar.spring.cinema.model.Movie;
import com.busvancar.spring.cinema.model.MovieSession;

	@Repository
	public interface MovieSessionRepository  extends JpaRepository<MovieSession, Integer> {

	/*
	final int ROWS = 12;

	List<MovieSession> getAllMovieSessions(int movieId);

	MovieSession insertMovieSession(MovieSession ms);

	void removeMovieSession(int movieId);

	MovieSession getMovieSession(int sessionID);

	int getMovieSessionBasePrice(int sessionID);

	double getPrice(int movieSessionBasePrice, int seat);

	void updateMovieSessionAvailableSeats(int movieSession, int availableSeats);
*/
}
