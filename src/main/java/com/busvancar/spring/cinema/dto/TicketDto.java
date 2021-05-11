package com.busvancar.spring.cinema.dto;

import java.sql.Timestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude
public class TicketDto {
	
	private int ticketId;
	private int seat;
	private int sessionId;
	private int price;
	private String genre;
	private int purchaserId;
	private String sessionToken;
	private Timestamp time;
	/*
	private String movieTitle;
	private int movieDuration;
	private Timestamp movieSessionTime;
	private int row;
	*/
}
