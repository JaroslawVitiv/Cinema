package com.busvancar.spring.cinema.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class MovieSessionDto {
	
	private int sessionId;
	private int movieId;
	private double price;
	private Timestamp dateTime;
	private String moviePoster;
	@NotNull
	@NotEmpty
	private String movieTitle;
	private String movieDescriptionEn;
	private String movieDescriptionUa;
	private int movieDuration;
	private String movieGenre;
	private String movieGenreUa;
	private int availableSeats;

}
