package com.busvancar.spring.cinema.exception;

import com.busvancar.spring.cinema.model.enums.ErrorType;

public class MovieNotFoundException extends ServiceException {
	
	private static final String DEFAULT_MESSAGE = "Movie is not found";
	
	public MovieNotFoundException() {
		super(DEFAULT_MESSAGE);
	}
	
	public MovieNotFoundException(String message) {
		super(message);
	}
	
	@Override
	public ErrorType getErrorType() {
		return ErrorType.DATABASE_ERROR_TYPE;
	}

}
