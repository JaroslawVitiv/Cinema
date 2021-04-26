package com.busvancar.spring.cinema.service.impl;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.busvancar.spring.cinema.dto.MovieSessionDto;
import com.busvancar.spring.cinema.model.MovieSession;
import com.busvancar.spring.cinema.repository.MovieSessionRepository;
import com.busvancar.spring.cinema.service.MovieSessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service 
@RequiredArgsConstructor
public class MovieSessionServiceImpl implements MovieSessionService{
	
	private final MovieSessionRepository msRepository;
	
	@Override
	public List<MovieSession> getAllMovieSessions(int movieId){
        List<MovieSession> listMovieSessions = msRepository.getAllMovieSessions(movieId);
        return listMovieSessions;
	}
	
	@Override
	public MovieSessionDto insertMovieSession(MovieSessionDto msDto) {
		MovieSession ms = mapMSDto2MS(msDto);
		ms = msRepository.insertMovieSession(ms);
		return mapMS2MSDto(ms);
	}	
	
	@Override
	public MovieSessionDto getMovieSession(int sessionID) {
		log.info("getMovieSession by sessionID: movie session - {}", sessionID);
		MovieSession ms = msRepository.getMovieSession(sessionID);
		return mapMS2MSDto(ms);
	}
	
	@Override
	public void removeMovieSession(int sessionID) {
		msRepository.removeMovieSession(sessionID);
	}
	
	@Override
	public int getMovieSessionBasePrice(int sessionID) {
		log.info("getMovieSessionBasePrice by sessionID: movie session - {}", sessionID);
		return (int) getMovieSession(sessionID).getPrice();
	}
	
	@Override
	public double getPrice(int movieSessionBasePrice, int seat) {
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		
		int tempSeat = seat-1;
		double priceIncrementRate = 1;
		while(tempSeat > ROWS) {
			priceIncrementRate += 0.049;
			tempSeat -= ROWS;
		}
		
		return Double.parseDouble(df.format(movieSessionBasePrice * priceIncrementRate / 100));
	}

	@Override
	public void updateMovieSessionAvailableSeats(int movieSession, int availableSeats) {
		msRepository.updateMovieSessionAvailableSeats(movieSession, availableSeats);		
	}
	
	private MovieSessionDto mapMS2MSDto(MovieSession ms) {
		return MovieSessionDto.builder()
				.sessionId(ms.getSessionId())
				.movieId(ms.getMovieId())
				.price(ms.getPrice())
				.dateTime(ms.getDateTime())
				.moviePoster(ms.getMoviePoster())
				.movieTitle(ms.getMovieTitle())
				.movieDescriptionEn(ms.getMovieDescriptionEn())
				.movieDescriptionUa(ms.getMovieDescriptionUa())
				.movieDuration(ms.getMovieDuration())
				.movieGenre(ms.getMovieGenre())
				.movieGenreUa(ms.getMovieGenreUa())
				.availableSeats(ms.getAvailableSeats())
				.build();
		
	}	
	
	private MovieSession mapMSDto2MS(MovieSessionDto msDto) {
		return MovieSession.builder()
				.sessionId(msDto.getSessionId())
				.movieId(msDto.getMovieId())
				.price(msDto.getPrice())
				.dateTime(msDto.getDateTime())
				.moviePoster(msDto.getMoviePoster())
				.movieTitle(msDto.getMovieTitle())
				.movieDescriptionEn(msDto.getMovieDescriptionEn())
				.movieDescriptionUa(msDto.getMovieDescriptionUa())
				.movieDuration(msDto.getMovieDuration())
				.movieGenre(msDto.getMovieGenre())
				.movieGenreUa(msDto.getMovieGenreUa())
				.availableSeats(msDto.getAvailableSeats())
				.build();
	}

	

	

}
