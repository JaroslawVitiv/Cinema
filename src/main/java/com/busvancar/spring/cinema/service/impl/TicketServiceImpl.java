package com.busvancar.spring.cinema.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.busvancar.spring.cinema.dto.MovieDto;
import com.busvancar.spring.cinema.dto.TicketDto;
import com.busvancar.spring.cinema.exception.MovieNotFoundException;
import com.busvancar.spring.cinema.exception.TicketNotFoundException;
import com.busvancar.spring.cinema.model.Movie;
import com.busvancar.spring.cinema.model.Ticket;
import com.busvancar.spring.cinema.model.User;
import com.busvancar.spring.cinema.repository.MovieRepository;
import com.busvancar.spring.cinema.repository.TicketRepository;
import com.busvancar.spring.cinema.service.MovieService;
import com.busvancar.spring.cinema.service.TicketService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class TicketServiceImpl implements TicketService{

	@Resource
	TicketRepository ticketRepository;

	@Override
	public TicketDto getTicket(Integer seat, Integer sessionId) {
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");    
		log.info("Get Ticket by seat - {} and session id - {} ", seat, sessionId);
		Ticket ticket = ticketRepository.findBySeatAndSessionId(seat, sessionId)
				.orElseThrow(TicketNotFoundException::new);
		System.out.print("userFound:"+ticket.toString());
		return mapTicket2TicketDto(ticket);	
	}
	
	@Override
	public TicketDto createTicket(@Valid TicketDto tDto) {
		
		log.info("Insert created ticket in db - {}", tDto.getTicketId());

		Ticket ticket = mapTicketDto2Ticket(tDto);
		if(!ticketRepository.findById(tDto.getTicketId()).isPresent()) {
			ticket = ticketRepository.save(ticket);
		}			
		return mapTicket2TicketDto(ticket);
	}
	
	

	@Override
	public void removeTicket(Integer sessionId) {
		// TODO Auto-generated method stub
		
	}

	
	
	@Override
	public List<Ticket> getAllTicketsList() {
		log.info("getAllTickets");
		return (List<Ticket>) ticketRepository.findAll();
	}
/*	
	

	
	
	
	
	

	@Override
	public TicketDto getTicket(int seat, int sessionId) {
		log.info("getTicket by seat: {} and sessionId - {}", seat, sessionId);
		Ticket ticket = ticketRepository.getTicket(seat, sessionId);
		return mapTicket2TicketDto(ticket);
	}

	

	@Override
	public void removeTicket(int seat, int sessionId, int purchaserId, String sessionToken) {
		log.info("removeTicket by seat {}, sessionId {}, purchaserId - {}, sessionToken - {} and sessionId - {}", seat,  sessionId,  purchaserId,  sessionToken);
		ticketRepository.removeTicket(seat,  sessionId,  purchaserId,  sessionToken);
		
	}

	@Override
	public Ticket[] getAllTickets(int sessionId) {
		log.info("getAllTickets by  sessionId {}",  sessionId);
		return ticketRepository.getAllTickets(sessionId);
	}

	

	@Override
	public int getTicketCount(String sessionToken) {
		log.info("getTicketCount by  sessionToken {}",  sessionToken);
		return ticketRepository.getTicketCount(sessionToken);
	}

	@Override
	public List<Ticket> getBookedUnpaidTickets(String sessionToken) {
		log.info("getBookedUnpaidTickets by  sessionToken {}",  sessionToken);
		return ticketRepository.getBookedUnpaidTickets(sessionToken);
	}

	@Override
	public void removeFromInvoice(int ticketId, String sessionToken) {
		log.info("removeFromInvoice by  ticketId - {}, sessionToken - {}",  ticketId,  sessionToken);
		 ticketRepository.removeFromInvoice(ticketId,  sessionToken);		
	}

	@Override
	public void purgeAllUnpaidTickets(String sessionToken) {
		log.info("purgeAllUnpaidTickets by sessionToken - {}",   sessionToken);
		ticketRepository.purgeAllUnpaidTickets(sessionToken);	
	}

	@Override
	public boolean setUserId2pay(int purchaserId, String sessionToken) {
		log.info("purgeAllUnpaidTickets by purchaserId - {} and sessionToken - {}",   purchaserId, sessionToken);
		return ticketRepository.setUserId2pay(purchaserId,  sessionToken) ;
	}

	@Override
	public double getTodaysSum(int purchaserId, String sessionToken) {
		log.info("getTodaysSum by purchaserId - {},  sessionToken - {}",   purchaserId, sessionToken);
		return ticketRepository.getTodaysSum(purchaserId,  sessionToken) ;
	}

	@Override
	public void clearAllUnpaid() {
		log.info("clearAllUnpaid is being called");
		ticketRepository.clearAllUnpaid() ;
	}

	@Override
	public int getBookedSeats(int sessionID) {
		log.info("getBookedSeats is called by sessionID - {}", sessionID);
		return ticketRepository.getBookedSeats(sessionID) ;
	}

	@Override
	public List<Ticket> getAllTicketsList(int purchaserID) {
		log.info("getAllTicketsList is called by purchaserID - {}", purchaserID);
		return ticketRepository.getAllTicketsList(purchaserID) ;
	}

	

	
	
	
	
*/
	
	private TicketDto mapTicket2TicketDto(Ticket ticket) {
		return TicketDto.builder()
				.ticketId(ticket.getTicketId())
				.sessionId(ticket.getSessionId())
				.price(ticket.getPrice())
				.genre(ticket.getGenre())
				.purchaserId(ticket.getPurchaserId())
				.sessionToken(ticket.getSessionToken())
				.time(ticket.getTime())
				.seat(ticket.getSeat())
				//.movieTitle(ticket.getMovieTitle())
				//.movieDuration(ticket.getMovieDuration())
				//.movieSessionTime(ticket.getMovieSessionTime())
				//.row(ticket.getRow())
				.build();
		
	}	
	
	private Ticket mapTicketDto2Ticket(TicketDto ticketDto) {
		return Ticket.builder()
				.ticketId(ticketDto.getTicketId())
				.sessionId(ticketDto.getSessionId())
				.price(ticketDto.getPrice())
				.genre(ticketDto.getGenre())
				.purchaserId(ticketDto.getPurchaserId())
				.sessionToken(ticketDto.getSessionToken())
				.time(ticketDto.getTime())
				.seat(ticketDto.getSeat())
				//.movieTitle(ticketDto.getMovieTitle())
				//.movieDuration(ticketDto.getMovieDuration())
				//.movieSessionTime(ticketDto.getMovieSessionTime())
				//.row(ticketDto.getRow())
				.build();
	}
}
