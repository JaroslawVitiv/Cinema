package com.busvancar.spring.cinema.controller.assembler;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.busvancar.spring.cinema.dto.UserDto;
import com.busvancar.spring.cinema.controller.UserController;
import com.busvancar.spring.cinema.controller.model.UserModel;

@Component
public class UserAssembler extends RepresentationModelAssemblerSupport<UserDto, UserModel> {
	
	public UserAssembler() {
		super(UserController.class, UserModel.class);
	}

	@Override
	public UserModel toModel(UserDto entity) {
		UserModel userModel = new UserModel(entity);
		
		Link findUser = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).findUser(entity.getEmail())).withRel("findUser");
		Link deleteUser = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).deleteUser(entity.getEmail())).withRel("deleteUser");

		userModel.add(findUser, deleteUser);
		return null;
	}
}
