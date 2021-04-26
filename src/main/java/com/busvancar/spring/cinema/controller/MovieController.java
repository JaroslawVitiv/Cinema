package com.busvancar.spring.cinema.controller;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
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
import org.springframework.web.client.RestTemplate;

import com.busvancar.spring.cinema.dto.MovieDto;
import com.busvancar.spring.cinema.dto.UserDto;
import com.busvancar.spring.cinema.interceptor.MovieInterceptor;
import com.busvancar.spring.cinema.model.Movie;
import com.busvancar.spring.cinema.model.MovieEntity;
import com.busvancar.spring.cinema.model.User;

import com.busvancar.spring.cinema.service.MovieService;
import com.busvancar.spring.cinema.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

	private final MovieService movieService;

	private static Map<String, Movie> movieRepo = new HashMap<>();

	@RequestMapping(value = "/products")
	public ResponseEntity<Object> getProduct() {
		return new ResponseEntity<>(movieRepo.values(), HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/all")
	public List<Movie> listAllMovies() {

		log.info("List all movies: "+movieService.listAllMovies().toString());
		return movieService.listAllMovies();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}")
	public MovieDto getMovie(@PathVariable int id) {
		return movieService.getMovie(id);
	}

	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/create")
	public MovieDto createMovie(@RequestBody MovieDto movieDto) {
		log.info("Create movie directly: {}", movieDto);
		return movieService.createMovie(movieDto);
	}
	
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public MovieDto insertMovie(@Valid @RequestBody MovieDto movieDto) {

		//// BeansUtil
		String genre = "Computer Science";
		String descriptionUa = "Опис українською";

		try {
			PropertyUtils.setSimpleProperty(movieDto, "genre", genre);
			PropertyUtils.setSimpleProperty(movieDto, "descriptionUa", descriptionUa);

		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}

		MovieEntity movieEntity = new MovieEntity();
		BeanUtils.copyProperties(movieEntity, movieDto);

		log.info("BeanUtils fitches:" + movieEntity.toString());

		log.info("Create movie: {}", movieDto);
		return movieService.insertMovie(movieDto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeMovie(@PathVariable int id) {
		movieService.removeMovie(id);
		return ResponseEntity.noContent().build();
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public MovieDto updateMoviePatch(@PathVariable int id, @RequestBody MovieDto movieDto) {
		return movieService.updateMovie(id, movieDto);
	}


	
	
	
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public MovieDto updateMoviePut(@PathVariable int id, @RequestBody MovieDto movieDto) {
		return movieService.updateMovie(id, movieDto);
	}
}
