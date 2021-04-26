package com.busvancar.spring.cinema.exception;

import com.busvancar.spring.cinema.model.enums.ErrorType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ServiceException extends RuntimeException{
	
	private ErrorType errorType;
	
	public ServiceException() {
		
	}

	public ServiceException(String message) {
		super(message);
	}
	
	public ErrorType getErrorType() {
		return ErrorType.FATAL_ERROR_TYPE;
	}
}
