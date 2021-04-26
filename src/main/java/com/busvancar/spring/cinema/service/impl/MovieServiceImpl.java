package com.busvancar.spring.cinema.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.busvancar.spring.cinema.dto.MovieDto;
import com.busvancar.spring.cinema.model.Movie;
import com.busvancar.spring.cinema.model.MovieEntity;
import com.busvancar.spring.cinema.repository.MovieRepository;
import com.busvancar.spring.cinema.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service 
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{
	
	private final MovieRepository movieRepository;
	
	@Override
	public List<Movie> listAllMovies(){
        List<Movie> listMovies = movieRepository.listAllMovies();
        return listMovies;
	}
	
	@Override
	public MovieDto updateMovie(int id, MovieDto movieDto) {
		Movie movie = mapMovieDtoToMovie(movieDto);
		movie = movieRepository.updateMovie(id, movie);
		
		return mapMovieToMovieDto(movie);
	}	

	@Override
	public MovieDto getMovie(int id) {
		log.info("getMovie by id: movie - {}", id);
		Movie movie = movieRepository.getMovie(id);
		
		return mapMovieToMovieDto(movie);
	}

	@Override
	public MovieDto insertMovie(MovieDto movieDto) {
		Movie movie = mapMovieDtoToMovie(movieDto);
		movie = movieRepository.insertMovie(movie);
		return mapMovieToMovieDto(movie);
	}

	@Override
	public void removeMovie(int id) {
		movieRepository.removeMovie(id);
	}


	private MovieDto mapMovieToMovieDto(Movie movie) {
		return MovieDto.builder()
				.id(movie.getId())
				.title(movie.getTitle())
				.descriptionUa(movie.getDescriptionUa())
				.descriptionEn(movie.getDescriptionEn())
				.duration(movie.getDuration())
				.genre(movie.getGenre())
				.genreUa(movie.getGenreUa())
				.genreId(movie.getGenreId())
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
				.genre(movieDto.getGenre())
				.genreUa(movieDto.getGenreUa())
				.genreId(movieDto.getGenreId())
				.poster(movieDto.getPoster())
				.price(movieDto.getPrice())
				.build();
		
		
	}

	@Override
	public MovieDto createMovie(MovieDto movieDto) {
		Movie movie = mapMovieDtoToMovie(movieDto);
		movie = movieRepository.createMovie(movie);
		return mapMovieToMovieDto(movie);
	}

}
