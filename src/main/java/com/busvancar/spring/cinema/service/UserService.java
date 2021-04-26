package com.busvancar.spring.cinema.service;

import java.util.List;

import com.busvancar.spring.cinema.dto.UserDto;
import com.busvancar.spring.cinema.model.User;

public interface UserService {

	UserDto findUser(String email);

	UserDto insertUser(UserDto userDto);

	UserDto updateUser(String email, UserDto userDto);

	void deleteUser(String email);
	
	List<User> listAllUsers();

}
