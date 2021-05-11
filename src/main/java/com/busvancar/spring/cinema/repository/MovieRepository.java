package com.busvancar.spring.cinema.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.busvancar.spring.cinema.model.Movie;

@Repository
public interface MovieRepository  extends JpaRepository<Movie, Integer> {

	
	Optional <Movie> findById(Integer id);

	void deleteById(Integer id);

	boolean existsById(Integer id);

}
