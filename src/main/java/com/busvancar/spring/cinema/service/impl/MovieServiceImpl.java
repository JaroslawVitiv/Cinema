  package com.busvancar.spring.cinema.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.busvancar.spring.cinema.dto.MovieDto;
import com.busvancar.spring.cinema.exception.MovieNotFoundException;
import com.busvancar.spring.cinema.model.Movie;
import com.busvancar.spring.cinema.repository.MovieRepository;
import com.busvancar.spring.cinema.service.MovieService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class MovieServiceImpl implements MovieService{

	@Resource
	MovieRepository movieRepository;

	@Override
	public List<Movie> listAllMovies(){
		log.info("List of all movies");
		return (List<Movie>) movieRepository.findAll();
	}
	
	@Override
	@Transactional
	public MovieDto saveOrUpdate(Integer id, MovieDto movieDto) {
		 log.info("Save movie in db - {}", movieDto.getTitle());
		 
		 Movie mv = movieRepository.findById(id)
			        .map(m -> {
			          m.setTitle(movieDto.getTitle());
			          m.setDuration(movieDto.getDuration());
			          m.setGenre(movieDto.getGenre());
			          m.setDescriptionEn(movieDto.getDescriptionEn());
			          m.setPrice(movieDto.getPrice());
			          return movieRepository.save(m);
			        	
			        }).orElseThrow(MovieNotFoundException::new);
			    return mapMovieToMovieDto(mv);
	}	

	@Override
	public MovieDto updateMovie(Integer id, MovieDto movieDto) {
		Movie movie = mapMovieDtoToMovie(movieDto);
		if(!movieRepository.existsById(id)) {
			throw new MovieNotFoundException();
		}
		return mapMovieToMovieDto(movieRepository.save(movie));	
	}
	
	@Override
	public MovieDto getMovie(Integer id) {
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");    
		log.info("getMovie by id: movie - {}", id);
		Movie movie = movieRepository.findById(id)
				.orElseThrow(MovieNotFoundException::new);
		System.out.print("userFound:"+movie.toString());
		return mapMovieToMovieDto(movie);		
	}

	@Override
	public MovieDto insertMovie(MovieDto movieDto) {
		 log.info("Insert created movie in db - {}", movieDto.getTitle());

		Movie movie = mapMovieDtoToMovie(movieDto);
		if(!movieRepository.findById(movieDto.getId()).isPresent()) {
			movie = movieRepository.save(movie);
		}			
		return mapMovieToMovieDto(movie);
	}

	@Override
	public void removeMovie(Integer id) {
		movieRepository.deleteById(id);		
	}
	
	private MovieDto mapMovieToMovieDto(Movie movie) {
		return MovieDto.builder()
				.id(movie.getId())
				.title(movie.getTitle())
				.descriptionUa(movie.getDescriptionUa())
				.descriptionEn(movie.getDescriptionEn())
				.duration(movie.getDuration())
				.genreId(movie.getGenreId())
				.genre(movie.getGenre())
				.genreUa(movie.getGenreUa())
				.poster(movie.getPoster())
				.price(movie.getPrice())
				.build();
	}	
	
	private Movie mapMovieDtoToMovie(MovieDto movieDto) {
		return Movie.builder()
				.id(movieDto.getId())
				.title(movieDto.getTitle())
				.descriptionUa(movieDto.getDescriptionUa())
				.descriptionEn(movieDto.getDescriptionEn())
				.duration(movieDto.getDuration())
				.genreId(movieDto.getGenreId())
				.genre(movieDto.getGenre())
				.genreUa(movieDto.getGenreUa())
				.poster(movieDto.getPoster())
				.price(movieDto.getPrice())
				.build();
	}
}
