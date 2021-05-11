package com.busvancar.spring.cinema.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.busvancar.spring.cinema.model.Movie;
import com.busvancar.spring.cinema.model.Ticket;
import com.busvancar.spring.cinema.model.User;

@Repository
public interface TicketRepository  extends JpaRepository<Ticket, Integer> {

	Optional<Ticket> findBySeatAndSessionId(Integer seat, Integer sessionId);


	/*
	Ticket getTicket(int seat, int sessionId);

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

	List<Ticket> getAllTicketsList(int purchaserID);

	List<Ticket> getAllTickets();
*/

	
}
