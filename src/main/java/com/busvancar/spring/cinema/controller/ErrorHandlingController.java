package com.busvancar.spring.cinema.controller;

import java.lang.invoke.MethodHandle;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import com.busvancar.spring.cinema.exception.UserNotFoundException;
import com.busvancar.spring.cinema.exception.ServiceException;

import com.busvancar.spring.cinema.model.enums.ErrorType;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.busvancar.spring.cinema.model.Error;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ErrorHandlingController {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public List<Error> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		log.error("handleMethodArgumentNotValidException: message {},  ", ex.getMessage());
		return ex.getBindingResult().getAllErrors().stream()
				.map(err -> new Error(err.getDefaultMessage(), ErrorType.VALIDATION_ERROR_TYPE, LocalDateTime.now()))
				.collect(Collectors.toList());
	}

	@ExceptionHandler(ServiceException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Error handleServiceException(ServiceException ex) {
		log.error("handleServiceException: message {} ", ex.getMessage());
		return new Error(ex.getMessage(), ex.getErrorType(), LocalDateTime.now());
	}

	
	@ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handleException(Exception ex) {
        log.error("handleException: message", ex);
        return new Error(ex.getMessage(), ErrorType.FATAL_ERROR_TYPE, LocalDateTime.now());
    }
}
