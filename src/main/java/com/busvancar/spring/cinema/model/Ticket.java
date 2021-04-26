package com.busvancar.spring.cinema.model;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Ticket {
	
	private int ticketId;
	private int seat;
	private int sessionId;
	private double price;
	private String genre;
	private int purchaserId;
	private String sessionToken;
	private Timestamp time;
	private String movieTitle;
	private int movieDuration;
	private Timestamp movieSessionTime;
	private int row; 
	

}
