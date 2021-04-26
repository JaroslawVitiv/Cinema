package com.busvancar.spring.cinema.repository.impl;

import java.math.BigInteger;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.busvancar.spring.cinema.model.MovieSession;
import com.busvancar.spring.cinema.repository.MovieSessionRepository;

@Component
public class MovieSessionRepositoryImpl implements MovieSessionRepository{
	
	private List<MovieSession> list = new ArrayList<>();
	
	@Override
	public List<MovieSession> getAllMovieSessions(int movieId) {
        return (List<MovieSession>) list.stream().filter(ms -> (ms.getMovieId() == movieId));
	}
	
	@Override
	public MovieSession insertMovieSession(MovieSession ms) {
		list.add(ms);
		return ms;
	}
	
	@Override
	public void removeMovieSession(int movieId) {
		list.removeIf(ms -> (ms.getMovieId() == movieId));
	}
	
	@Override
	public MovieSession getMovieSession(int sessionID) {
		return list.stream()
				.filter(ms -> (ms.getSessionId() == sessionID))
				.findFirst()
				.orElseThrow(RuntimeException::new);
	}
	
	
	@Override
	public int getMovieSessionBasePrice(int sessionID) {
		MovieSession movieSession = list.stream()
				.filter(ms -> (ms.getSessionId() == sessionID))
				.findFirst()
				.orElseThrow(RuntimeException::new);
		return (int) movieSession.getPrice();
	}
	
	@Override
	public double getPrice(int movieSessionBasePrice, int seat) {
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		
		int tempSeat = seat-1;
		double priceIncrementRate = 1;
		while(tempSeat > ROWS ) {
			priceIncrementRate += 0.049;
			tempSeat -= ROWS;
		}
		
		return Double.parseDouble(df.format(movieSessionBasePrice * priceIncrementRate / 100));
	}

	@Override
	public void updateMovieSessionAvailableSeats(int movieSession, int availableSeats) {
		list.stream()
			.filter(ms -> (ms.getSessionId() == movieSession))
			.forEach(ms -> ms.setAvailableSeats(availableSeats));
	}
	
		

}
