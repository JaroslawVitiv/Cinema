package com.busvancar.spring.cinema.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.busvancar.spring.cinema.dto.UserDto;
import com.busvancar.spring.cinema.exception.UserNotFoundException;
import com.busvancar.spring.cinema.model.Movie;
import com.busvancar.spring.cinema.model.User;
import com.busvancar.spring.cinema.repository.UserRepository;
import com.busvancar.spring.cinema.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service 
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	
	@Override
	public UserDto findUser(String email) {
		log.info("getUser by email: user - {}", email);
		User user = userRepository.findByEmail(email)
				.orElseThrow(UserNotFoundException::new);
		return mapUserToUserDto(user);
	}

	@Override
	public UserDto insertUser(UserDto userDto) {
		User user = mapUserDtoToUser(userDto);
		user = userRepository.save(user);
		return mapUserToUserDto(user);
	}	
	
	@Override
	public UserDto updateUser(String email, UserDto userDto) {
		log.info("Update user in db - {}"+userDto);
		User user = mapUserDtoToUser(userDto);
		if(!userRepository.existsByEmail(email)) {
			throw new UserNotFoundException();
		}
		user = userRepository.save(user);
		return mapUserToUserDto(user);
	}	

	@Override
	public void deleteUser(String email) {
		log.info("delete user - {} "+email);
		User user = userRepository.findByEmail(email)
			.orElseThrow(UserNotFoundException::new);
		userRepository.delete(user);
	}

	private UserDto mapUserToUserDto(User user) {
		return UserDto.builder()
				.id(user.getId())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.email(user.getEmail())
				.password(user.getPassword())
				.admin(user.getAdmin())
				.revenue(user.getRevenue())
				.generatedLong(user.getGeneratedLong())
				.build();
	}	
	
	private User mapUserDtoToUser(UserDto userDto) {
		return User.builder()
				.id(userDto.getId())
				.firstName(userDto.getFirstName())
				.lastName(userDto.getLastName())
				.email(userDto.getEmail())
				.password(userDto.getPassword())
				.admin(userDto.getAdmin())
				.revenue(userDto.getRevenue())
				.generatedLong(userDto.getGeneratedLong())
				.build();
	}

	@Override
	public List<User> listAllUsers() {
		 List<User> listUsers = userRepository.findAll();
	     return listUsers;
	}

}
