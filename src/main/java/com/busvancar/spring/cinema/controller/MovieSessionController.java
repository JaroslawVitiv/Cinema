package com.busvancar.spring.cinema.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.busvancar.spring.cinema.dto.MovieDto;
import com.busvancar.spring.cinema.dto.MovieSessionDto;
import com.busvancar.spring.cinema.dto.UserDto;
import com.busvancar.spring.cinema.model.MovieSession;
import com.busvancar.spring.cinema.service.MovieSessionService;
import com.busvancar.spring.cinema.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@EnableAutoConfiguration
@RequestMapping("/sessions")
public class MovieSessionController {

	@Autowired
	MovieSessionService msService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/post")
	public MovieSessionDto insertMovieSession(@Valid @RequestBody MovieSessionDto msDto) {
		log.info("Create movie session: {}", msDto.toString());
		msService.insertMovieSession(msDto);
		return msDto;
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/list/{movieId}")
	public List<MovieSession> getAllMovieSessions(@PathVariable Integer movieId) {
		log.info("Get movie session by movieId - {}", movieId);
		return msService.getAllMovieSessions(movieId);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/id/{id}")
	public MovieSessionDto getMovieSession(@PathVariable Integer id) {
		log.info("Get movie session by sessionID - {}", id);
		return msService.getMovieSession(id);
	}
	

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void removeMovieSession(@PathVariable Integer id) {
		log.info("Deleting movie: {}", msService.toString());
		msService.removeMovieSession(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/price/{id}")
	public int getMovieSessionBasePrice(@PathVariable Integer id) {
		return msService.getMovieSessionBasePrice(id);
	}

	

	@ResponseStatus(HttpStatus.OK)
	@PatchMapping("/update/{movieSession}/{availableSeats}")
	public void updateMovieSessionAvailableSeats(@PathVariable Integer movieSession, @PathVariable Integer availableSeats) {
		msService.updateMovieSessionAvailableSeats(movieSession, availableSeats);
	}
}
