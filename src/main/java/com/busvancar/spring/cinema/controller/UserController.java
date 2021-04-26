package com.busvancar.spring.cinema.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import com.busvancar.spring.cinema.api.UserApi;
import com.busvancar.spring.cinema.controller.assembler.UserAssembler;
import com.busvancar.spring.cinema.controller.model.UserModel;
import com.busvancar.spring.cinema.dto.UserDto;
import com.busvancar.spring.cinema.model.User;
import com.busvancar.spring.cinema.repository.UserRepository;
import com.busvancar.spring.cinema.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/users")
@RequiredArgsConstructor
@SessionAttributes("login")
public class UserController implements UserApi {
		
	private final UserService userService;
	private final UserAssembler userAssembler;
	
	@Override
	public UserModel findUser(String email) {
		UserDto user = userService.findUser(email);
		log.info("Found user - {}", user);
		return userAssembler.toModel(user);
	}

	@Override
	public UserModel insertUser(UserDto userDto) {
		log.info("Create user: {}", userDto);
		UserDto user = userService.insertUser(userDto);
		return userAssembler.toModel(user);
	}

	@Override
	public UserModel updateUser(String email, UserDto userDto) {
		UserDto user = userService.updateUser(email, userDto);
		return userAssembler.toModel(user);
	}

	@Override
	public ResponseEntity<Void> deleteUser(String email) {
		log.info("Deleting user: {}", userService.findUser(email));
		userService.deleteUser(email);
		return ResponseEntity.noContent().build();
	}

	@Override
	public long login(String name, UserDto userDto, HttpSession session) {

		Random random = new Random();
		System.out.println("Hello " + name);

		final long usersSessionId = random.nextLong();

		if (session.getAttribute("login") == null)
			session.setAttribute("login", usersSessionId);

		userDto.setGeneratedLong((long) session.getAttribute("login"));
		return userDto.getGeneratedLong();
	}

	@Override
	public List<User> listAllUsers() {
		System.out.println(userService.listAllUsers().toString()+"+UserController");
		return userService.listAllUsers();
	}

}
