package com.busvancar.spring.cinema.model;

import java.time.LocalDateTime;
import com.busvancar.spring.cinema.model.enums.ErrorType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Error {
	
	private String message;
	private ErrorType errorType;
	private LocalDateTime timeStamp;
	
}
