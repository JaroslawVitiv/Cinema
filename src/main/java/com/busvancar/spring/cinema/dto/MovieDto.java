package com.busvancar.spring.cinema.dto;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.busvancar.spring.cinema.model.MovieSession;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
///@JsonInclude(Include.NON_NULL)
public class MovieDto {
	
	private int id;
	///@NotNull
	///@NotEmpty
	private String title;
	private String descriptionUa;
	private String descriptionEn;
	private int duration;
	private String genre;
	private String genreUa;
	private int genreId;
	private String poster;
	private double price;
	
    private Map<String, MovieSession> movieSessions = new HashMap<>();

	
	
}
