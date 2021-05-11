package com.busvancar.spring.cinema.dto;

import javax.persistence.Column;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class UserDto {

	
	private int id;
	
	@NotNull
	@NotEmpty
	private String firstName;
	@NotNull(message = "Should not be NULL")
	@NotEmpty
	private String lastName;
	@NotNull 	
	@Email
    private String email;
	@NotNull
	@NotEmpty
	@Size(min = 6, max = 20)
	private String password;
	private String repeatPassword;
	private int admin;
	private double revenue;
	private long generatedLong;

}
