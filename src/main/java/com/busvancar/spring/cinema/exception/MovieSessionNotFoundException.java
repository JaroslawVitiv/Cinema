package com.busvancar.spring.cinema.exception;

import com.busvancar.spring.cinema.model.enums.ErrorType;

public class MovieSessionNotFoundException extends ServiceException {
	
	private static final String DEFAULT_MESSAGE = "Movie session NOT found";
	
	public MovieSessionNotFoundException() {
		super(DEFAULT_MESSAGE);
	}
	
	public MovieSessionNotFoundException(String message) {
		super(message);
	}
	
	@Override
	public ErrorType getErrorType() {
		return ErrorType.DATABASE_ERROR_TYPE;
	}

}
