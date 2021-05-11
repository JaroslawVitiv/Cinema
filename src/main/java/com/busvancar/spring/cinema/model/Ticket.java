package com.busvancar.spring.cinema.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder

@Table(name="ticket")
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticket_id")
	private Integer ticketId;
	
	@Column(name = "seat")
	private Integer seat;

	@Column(name = "session_id")
	private Integer sessionId;
	
	@Column(name = "price")
	private Integer price;
	
	@Column(name = "purchaser_id")
	private Integer purchaserId;
	
	@Column(name = "session_token")
	private String sessionToken;
	
	@Column(name = "time")
	private Timestamp time;

	@Transient
	private String movieTitle;
	@Transient
	private Integer movieDuration;
	@Transient
	private Timestamp movieSessionTime;
	@Transient
	private Integer row; 
	@Transient
	private String genre;

}
