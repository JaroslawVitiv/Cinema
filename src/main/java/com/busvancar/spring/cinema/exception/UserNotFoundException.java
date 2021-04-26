package com.busvancar.spring.cinema.exception;

import com.busvancar.spring.cinema.model.enums.ErrorType;

public class UserNotFoundException extends ServiceException {
	
	private static final String DEFAULT_MESSAGE = "User is not found";
	
	public UserNotFoundException() {
		super(DEFAULT_MESSAGE);
	}
	
	public UserNotFoundException(String message) {
		super(message);
	}
	
	@Override
	public ErrorType getErrorType() {
		return ErrorType.DATABASE_ERROR_TYPE;
	}

}
