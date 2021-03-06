package com.busvancar.spring.cinema.service;

import java.util.List;

import javax.validation.Valid;

import com.busvancar.spring.cinema.dto.TicketDto;
import com.busvancar.spring.cinema.model.Ticket;
import com.busvancar.spring.cinema.model.User;

public interface TicketService {

	TicketDto getTicket(Integer seat, Integer sessionId);

	TicketDto createTicket(@Valid TicketDto tDto);

	void removeTicket(Integer sessionId);

	List<Ticket> getAllTicketsList();


	//TicketDto getTicket(int seat, int sessionId);

	//void removeTicket(int seat, int sessionId, int purchaserId, String sessionToken);

	//Ticket[] getAllTickets(int sessionId);

	//int getTicketCount(String sessionToken);

	//List<Ticket> getBookedUnpaidTickets(String sessionToken);

	//void removeFromInvoice(int ticketId, String sessionToken);

	//void purgeAllUnpaidTickets(String sessionToken);

	//boolean setUserId2pay(int purchaserId, String sessionToken);

	//void clearAllUnpaid();

	//int getBookedSeats(int sessionID);

	//List<Ticket> getAllTicketsList(int purchaserID);

	//double getTodaysSum(int purchaserId, String sessionToken);



	
}
