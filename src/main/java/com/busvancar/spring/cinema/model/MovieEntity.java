package com.busvancar.spring.cinema.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
public class MovieEntity {
	
	  private Map<String, MovieSession> movieSessions = new HashMap<>();

}
