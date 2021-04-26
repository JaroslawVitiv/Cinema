package com.busvancar.spring.cinema.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.busvancar.spring.cinema.dto.MovieSessionDto;
import com.busvancar.spring.cinema.dto.UserDto;
import com.busvancar.spring.cinema.model.MovieSession;
import com.busvancar.spring.cinema.service.MovieSessionService;
import com.busvancar.spring.cinema.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/movies/sessions")
@RequiredArgsConstructor
public class MovieSessionController {

	private final MovieSessionService msService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public MovieSessionDto insertMovieSession(@Valid @RequestBody MovieSessionDto msDto) {
		log.info("Create movie session: {}", msDto);
		return msService.insertMovieSession(msDto);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/fromMovie/{movieId}")
	public List<MovieSession> getAllMovieSessions(int movieId) {
		List<MovieSession> listMovieSessions = msService.getAllMovieSessions(movieId);
		return listMovieSessions;
	}

	@RequestMapping(value = "/{sessionID}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeMovieSession(@PathVariable int sessionID) {
		msService.removeMovieSession(sessionID);
		return ResponseEntity.noContent().build();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/price/{sessionID}")
	public int getMovieSessionBasePrice(@PathVariable int sessionID) {
		return msService.getMovieSessionBasePrice(sessionID);
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/update/{sessionID}/{availableSeats}")
	public void updateMovieSessionAvailableSeats(int movieSession, int availableSeats) {
		msService.updateMovieSessionAvailableSeats(movieSession, availableSeats);
	}

}
