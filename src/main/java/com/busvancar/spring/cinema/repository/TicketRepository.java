package com.busvancar.spring.cinema.repository;

import java.time.LocalDate;
import java.util.List;

import com.busvancar.spring.cinema.model.Ticket;
import com.busvancar.spring.cinema.model.User;

public interface TicketRepository {

	Ticket getTicket(int seat, int sessionId);

	Ticket createTicket(Ticket ticket);

	void removeTicket(int seat, int sessionId, int purchaserId, String sessionToken);

	Ticket[] getAllTickets(int sessionId);

	int getTicketCount(String sessionToken);

	List<Ticket> getBookedUnpaidTickets(String sessionToken);

	void removeFromInvoice(int ticketId, String sessionToken);

	void purgeAllUnpaidTickets(String sessionToken);

	boolean setUserId2pay(int purchaserId, String sessionToken);

	double getTodaysSum(int purchaserId, String sessionToken);

	void clearAllUnpaid();

	int getBookedSeats(int movieSession);

	List<Ticket> getAllTickets(User user);

	Ticket getTicket(int ticketId, String sessionToken);

	Ticket getTicket(int ticketId);

	List<Ticket> getAllTicketsList(int purchaserID);


	
}
