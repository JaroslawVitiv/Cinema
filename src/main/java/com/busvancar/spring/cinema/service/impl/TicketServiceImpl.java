package com.busvancar.spring.cinema.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.busvancar.spring.cinema.dto.MovieDto;
import com.busvancar.spring.cinema.dto.TicketDto;
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
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService{
	
	private final TicketRepository ticketRepository;
	
	/*
	@Override
	public List<Movie> listAllMovies(){
        List<Movie> listMovies = movieRepository.listAllMovies();
        return listMovies;
	}
	
	@Override
	public MovieDto updateMovie(int id, MovieDto movieDto) {
		Movie movie = mapMovieDtoToMovie(movieDto);
		movie = movieRepository.updateMovie(id, movie);
		return mapMovieToMovieDto(movie);
	}	

	@Override
	public MovieDto getMovie(int id) {
		log.info("getMovie by id: movie - {}", id);
		Movie movie = movieRepository.getMovie(id);
		return mapMovieToMovieDto(movie);
	}

	@Override
	public MovieDto insertMovie(MovieDto movieDto) {
		Movie movie = mapMovieDtoToMovie(movieDto);
		movie = movieRepository.insertMovie(movie);
		return mapMovieToMovieDto(movie);
	}

	@Override
	public void removeMovie(int id) {
		movieRepository.removeMovie(id);
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
				.movieTitle(ticket.getMovieTitle())
				.movieDuration(ticket.getMovieDuration())
				.movieSessionTime(ticket.getMovieSessionTime())
				.row(ticket.getRow())
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
				.movieTitle(ticketDto.getMovieTitle())
				.movieDuration(ticketDto.getMovieDuration())
				.movieSessionTime(ticketDto.getMovieSessionTime())
				.row(ticketDto.getRow())
				.build();
	}

	@Override
	public TicketDto getTicket(int seat, int sessionId) {
		log.info("getTicket by seat: {} and sessionId - {}", seat, sessionId);
		Ticket ticket = ticketRepository.getTicket(seat, sessionId);
		return mapTicket2TicketDto(ticket);
	}

	@Override
	public TicketDto createTicket(@Valid TicketDto tDto) {
		
		Ticket ticket = mapTicketDto2Ticket(tDto);
		ticket = ticketRepository.createTicket(ticket);
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

	@Override
	public TicketDto getTicket(int ticketId) {
		Ticket ticket = ticketRepository.getTicket(ticketId);
		return mapTicket2TicketDto(ticket);
	}

}
