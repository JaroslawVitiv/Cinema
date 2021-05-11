package com.busvancar.spring.cinema.exception;

import com.busvancar.spring.cinema.model.enums.ErrorType;

public class TicketNotFoundException extends ServiceException {
	
	private static final String DEFAULT_MESSAGE = "Ticket not found";
	
	public TicketNotFoundException() {
		super(DEFAULT_MESSAGE);
	}
	
	public TicketNotFoundException(String message) {
		super(message);
	}
	
	@Override
	public ErrorType getErrorType() {
		return ErrorType.DATABASE_ERROR_TYPE;
	}

}
