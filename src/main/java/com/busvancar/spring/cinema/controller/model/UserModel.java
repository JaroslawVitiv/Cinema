package com.busvancar.spring.cinema.controller.model;

import org.springframework.hateoas.RepresentationModel;

import com.busvancar.spring.cinema.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class UserModel extends RepresentationModel<UserModel>{
	
	@JsonUnwrapped
	private UserDto userDto;

}
