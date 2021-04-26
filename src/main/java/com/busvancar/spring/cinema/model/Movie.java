package com.busvancar.spring.cinema.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Movie {
	
	private int id;
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
