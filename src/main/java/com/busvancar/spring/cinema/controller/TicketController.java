package com.busvancar.spring.cinema.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.busvancar.spring.cinema.dto.TicketDto;
import com.busvancar.spring.cinema.model.Ticket;
import com.busvancar.spring.cinema.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/tickets")
public class TicketController {

	@Autowired
	TicketService ticketService;
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/get/{seat}/{sessionId}")
	public TicketDto getTicket(@PathVariable Integer seat, @PathVariable Integer sessionId) {
		return ticketService.getTicket(seat, sessionId);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public TicketDto createTicket(@Valid @RequestBody TicketDto tDto) {
		log.info("Create ticket: {}", tDto);
		return ticketService.createTicket(tDto);
	}

	@RequestMapping(value = "/{seat}/{sessionId}/{purchaserId}/{sessionToken}", method = RequestMethod.DELETE)
	//public ResponseEntity<Void> removeTicket(@PathVariable int seat, @PathVariable int sessionId, @PathVariable int purchaserId, @PathVariable String sessionToken) {
	public ResponseEntity<Void> removeTicket(@PathVariable int sessionId) {
		ticketService.removeTicket(sessionId);
		return ResponseEntity.noContent().build();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/all")
	public List<Ticket> getAllTicketsList() {
		return ticketService.getAllTicketsList();
	}
	
	
	
	
	
	/*
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/all/purchaser/{purchaserId}")
	public List<Ticket> getAllTicketsList(int purchaserID) {
		return ticketService.getAllTicketsList(purchaserID);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{seat}/{sessionId}")
	public boolean isCreated(@PathVariable int seat, @PathVariable int sessionId) {
		TicketDto tDto = null;
		tDto = ticketService.getTicket(seat, sessionId);
		boolean isCreated = (tDto != null);
		log.info("Ticket is created: {}", isCreated);
		return isCreated;
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/session/{sessionId}")
	public Ticket[] getAllTickets(@PathVariable int sessionId) {
		Ticket[] tickets = ticketService.getAllTickets(sessionId);
		return tickets;
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/count/{sessionToken}")
	public int getTicketCount(@PathVariable String sessionToken) {
		return ticketService.getTicketCount(sessionToken);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/booked/unpaid/{sessionToken}")
	public List<Ticket> getBookedUnpaidTickets(@PathVariable String sessionToken) {
		return ticketService.getBookedUnpaidTickets(sessionToken);
	}

	@RequestMapping(value = "/remove/fromInvoice/{ticketId}/{sessionToken}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeFromInvoice(@PathVariable int ticketId, @PathVariable String sessionToken) {
		ticketService.removeFromInvoice(ticketId, sessionToken);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/remove/all/unpaid/session/{sessionToken}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> purgeAllUnpaidTickets(@PathVariable String sessionToken) {
		ticketService.purgeAllUnpaidTickets(sessionToken);
		return ResponseEntity.noContent().build();
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/purchaser/{purchaserId}/{sessionToken}")
	public boolean setUserId2pay(@PathVariable int purchaserId, @PathVariable String sessionToken) {
		return ticketService.setUserId2pay(purchaserId, sessionToken);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/today/sum/{purchaserId}/{sessionToken}")
	public double getTodaysSum(@PathVariable int purchaserId, @PathVariable String sessionToken) {
		return ticketService.getTodaysSum(purchaserId, sessionToken);
	}

	@RequestMapping(value = "/remove/all/unpaid", method = RequestMethod.DELETE)
	public ResponseEntity<Void> clearAllUnpaid() {
		ticketService.clearAllUnpaid();
		return ResponseEntity.noContent().build();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/session/{sessionID}")
	public int getBookedSeats(@PathVariable int sessionID) {
		return ticketService.getBookedSeats(sessionID);
	}

*/
	
}
