package com.busvancar.spring.cinema.repository.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.busvancar.spring.cinema.dto.TicketDto;
import com.busvancar.spring.cinema.model.Movie;
import com.busvancar.spring.cinema.model.Ticket;
import com.busvancar.spring.cinema.model.User;
import com.busvancar.spring.cinema.repository.TicketRepository;
import com.busvancar.spring.cinema.repository.UserRepository;

@Component
public class TicketRepositoryImpl implements TicketRepository{
	
	private List<Ticket> list = new ArrayList<>();
	
	@Override
	public Ticket getTicket(int seat, int sessionId) {
		return list.stream()
				.filter(ticket -> (ticket.getSeat() == seat))
				.filter(ticket -> (ticket.getSessionId() == sessionId))
				.findFirst()
				.orElseThrow(RuntimeException::new);
	}
	
	
	@Override
	public Ticket createTicket(Ticket ticket) {
		list.add(ticket);
		return ticket;
	}
	
	@Override
	public void removeTicket(int seat, int sessionId, int purchaserId, String sessionToken) {
		list.removeIf(ticket -> (
				(ticket.getSeat() == seat) 
				&& (ticket.getSessionId() == sessionId) 
				&& (ticket.getPurchaserId() == purchaserId) 
				&& (ticket.getSessionToken() == sessionToken)));
	}
	@Override
	public Ticket[] getAllTickets(int sessionId) {
		Ticket[] tickets = new Ticket[list.size()];
		
		list.stream()
		.filter(ticket -> (ticket.getSessionId() == sessionId))
		.collect(Collectors.toList());
		
		list.toArray(tickets);
		return tickets;
	}
	
	@Override
	public int getTicketCount(String sessionToken) {
		list.stream()
		.filter(ticket -> (ticket.getSessionToken().equals(sessionToken)))
		.collect(Collectors.toList());
		
		return list.size();
	}
	
	///!!This is a FAKE!!
	@Override
	public List<Ticket> getBookedUnpaidTickets(String sessionToken) {
		return list.stream().filter(ticket -> ticket.getSessionToken().equals(sessionToken)).collect(Collectors.toList());
	}
	
	///!!This is a FAKE!!
	@Override
	public void removeFromInvoice(int ticketId, String sessionToken) {
		list.removeIf(ticket -> (ticket.getTicketId() == ticketId && ticket.getSessionToken().equals(sessionToken)));
	}
	
	///!!This is a FAKE!!
	@Override
	public void purgeAllUnpaidTickets(String sessionToken) {
		list.removeIf(ticket -> (ticket.getSessionToken().equals(sessionToken)));
	}

	@Override
	public boolean setUserId2pay(int purchaserId,  String sessionToken) {
		Ticket t = list.stream()
		.filter(ticket -> (ticket.getPurchaserId() == 0 && ticket.getSessionToken().equals(sessionToken)))
		.findFirst()
		.orElseThrow(RuntimeException::new);
		
		t.setPurchaserId(purchaserId);
		return (t.getPurchaserId() > 0);
	}
	
	@Override
	public double getTodaysSum(int purchaseId, String sessionToken) {
		double sum = list.stream()
			.filter(ticket -> (ticket.getPurchaserId() == purchaseId && ticket.getSessionToken().equals(sessionToken)))
			.mapToDouble(ticket -> ticket.getPrice())
			.sum();
		
		return sum;
	}

	@Override
	public void clearAllUnpaid() {
		list.removeIf(ticket -> (ticket.getPurchaserId() == 0 && ticket.getTime().before(Timestamp.from(Instant.now()))));
	}
	
	
	@Override
	public int getBookedSeats(int movieSession) {
		return (int) list.stream()
			.filter(ticket -> (ticket.getPurchaserId() == movieSession))
			.count();		
	}
	
	@Override
	public List<Ticket> getAllTickets(User user) {
		return (List<Ticket>) list.stream()
			.filter(ticket -> (ticket.getPurchaserId() == user.getId()));
		
	}
	
	@Override
	public Ticket getTicket(int ticketId, String sessionToken) {
		return list.stream()
				.filter(ticket -> (ticket.getTicketId() == ticketId && ticket.getSessionToken().equals(sessionToken)))
				.findFirst()
				.orElseThrow(RuntimeException::new);
		
		
	}
	
	@Override
	public Ticket getTicket(int ticketId) {
		return list.stream()
				.filter(ticket -> (ticket.getTicketId() == ticketId))
				.findFirst()
				.orElseThrow(RuntimeException::new);
	}


	@Override
	public List<Ticket> getAllTicketsList(int purchaserID) {
		return (List<Ticket>) list.stream()
				.filter(ticket -> (ticket.getPurchaserId() == purchaserID));
			
	}



}
