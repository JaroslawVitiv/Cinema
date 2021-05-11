package com.busvancar.spring.cinema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.busvancar.spring.cinema.controller.model.MovieModel;
import com.busvancar.spring.cinema.dto.MovieDto;
import com.busvancar.spring.cinema.model.Employee;
import com.busvancar.spring.cinema.model.Movie;
import com.busvancar.spring.cinema.service.EmployeeService;
import com.busvancar.spring.cinema.service.MovieService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/movies")
public class MovieController {

	@Autowired
	MovieService movieService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/post")
	public MovieDto insertMovie(@RequestBody MovieDto movieDto) {
		 log.info("Create movie: {}", movieDto.getTitle());
		 movieService.insertMovie(movieDto);
	     return movieDto;
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/get/{id}")
	public MovieDto getMovie(@PathVariable Integer id) {
		log.info("Found movie - {}", id);
		return movieService.getMovie(id);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PatchMapping("/update/{id}")
	public MovieDto update(@PathVariable Integer id, @RequestBody MovieDto movieDto) {
		log.info("Updating movie - {} ", movieDto.getTitle());
		movieService.saveOrUpdate(id, movieDto);
		return movieDto;
	}
	
	@DeleteMapping("/delete/{id}")
	public void removeMovie(@PathVariable(value="id") Integer id) {
		log.info("Deleting movie: {}", movieService.getMovie(id));
		movieService.removeMovie(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/all")
	public List<Movie> listAllMovies() {
		return movieService.listAllMovies();
	}
	
	
	
}
