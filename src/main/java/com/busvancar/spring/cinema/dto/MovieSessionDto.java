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
	
	private int id;
	private int movieId;
	private int price;
	private Timestamp dateTime;
	private int availableSeats;
	

}
