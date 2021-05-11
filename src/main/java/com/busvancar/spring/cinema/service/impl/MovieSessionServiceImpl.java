package com.busvancar.spring.cinema.service.impl;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.busvancar.spring.cinema.dto.MovieSessionDto;
import com.busvancar.spring.cinema.model.Movie;
import com.busvancar.spring.cinema.model.MovieSession;
import com.busvancar.spring.cinema.repository.MovieSessionRepository;
import com.busvancar.spring.cinema.service.MovieSessionService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service 
@Transactional
public class MovieSessionServiceImpl implements MovieSessionService{
	
	@Resource
	MovieSessionRepository msRepository;
	
	@Override
	public List<MovieSession> getAllMovieSessions(Integer movieId) {
		 log.info("Get list of movie sessions by movieIDb - {}", movieId);
		 Integer movieIdentificator[] = { movieId };

		 Iterable<Integer> iterable = Arrays.asList(movieIdentificator);
		 
		return (List<MovieSession>) msRepository
				.findAll()
				.stream()
				.filter(ms -> ms.getMovieId() == movieId)
				.collect(Collectors.toList());
	}

	@Override
	public MovieSessionDto getMovieSession(Integer id) {
		log.info("Get MovieSession by sessionID: {}", id);
		MovieSession ms = msRepository.findById(id).get();
		return mapMovieSession2MovieSessionDto(ms);
	}
	
	
		
		
	
	@Override
	public MovieSessionDto insertMovieSession(@Valid MovieSessionDto msDto) {
		 log.info("Insert created movie session in db - {}", msDto);

			MovieSession movieSession = mapMovieSessionDto2MovieSession(msDto);
			if(!msRepository.findById((int) msDto.getId()).isPresent()) {
				movieSession = msRepository.save(movieSession);
			}			
			return mapMovieSession2MovieSessionDto(movieSession);
	}
	
	@Override
	public void removeMovieSession(Integer id) {
		msRepository.deleteById(id);
	}

	
		
	@Override
	public Integer getMovieSessionBasePrice(Integer id) {
		log.info("getMovieSessionBasePrice by sessionID: movie session - {}", id);
		return getMovieSession(id).getPrice();
	}

	
	
	
	@Override
	public void updateMovieSessionAvailableSeats(Integer movieSession, Integer availableSeats) {
		log.info("Update movie session: {} with a new number of available seats {}", movieSession, availableSeats );
		MovieSession ms = msRepository.findById(movieSession).get();
		ms.setAvailableSeats(availableSeats);
		msRepository.save(ms);		
	}

	/*
	
	
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

	
	*/
	
	private MovieSessionDto mapMovieSession2MovieSessionDto(MovieSession ms) {
		return MovieSessionDto.builder()
				.id(ms.getId())
				.movieId(ms.getMovieId())
				.price(ms.getPrice())
				.dateTime(ms.getDateTime())
				//.moviePoster(ms.getMoviePoster())
				//.movieTitle(ms.getMovieTitle())
				//.movieDescriptionEn(ms.getMovieDescriptionEn())
				//.movieDescriptionUa(ms.getMovieDescriptionUa())
				//.movieDuration(ms.getMovieDuration())
				//.movieGenre(ms.getMovieGenre())
				//.movieGenreUa(ms.getMovieGenreUa())
				.availableSeats(ms.getAvailableSeats())
				.build();
		
	}	
	
	private MovieSession mapMovieSessionDto2MovieSession(MovieSessionDto msDto) {
		return MovieSession.builder()
				.id(msDto.getId())
				.movieId(msDto.getMovieId())
				.price(msDto.getPrice())
				.dateTime(msDto.getDateTime())
				//.moviePoster(msDto.getMoviePoster())
				//.movieTitle(msDto.getMovieTitle())
				//.movieDescriptionEn(msDto.getMovieDescriptionEn())
				//.movieDescriptionUa(msDto.getMovieDescriptionUa())
				//.movieDuration(msDto.getMovieDuration())
				//.movieGenre(msDto.getMovieGenre())
				//.movieGenreUa(msDto.getMovieGenreUa())
				.availableSeats(msDto.getAvailableSeats())
				.build();
	}

	

	
	

}
