package com.busvancar.spring.cinema.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import com.busvancar.spring.cinema.dto.UserDto;
import com.busvancar.spring.cinema.exception.UserNotFoundException;
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
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");    
		log.info("getUser by email: user - {}", email);
		User user = userRepository.findByEmail(email)
				.orElseThrow(UserNotFoundException::new);
		System.out.print("userFound:"+user.toString());
		return mapUserToUserDto(user);
	}

	@Override
	public UserDto insertUser(UserDto userDto) {
		User user = mapUserDtoToUser(userDto);
		if(!userRepository.findByEmail(userDto.getEmail()).isPresent()) {
			user = userRepository.save(user);
		}			
		return mapUserToUserDto(user);
	}	
	
	
	  @Override
	  @Transactional
	  public UserDto updateUser(String email, UserDto userDto) {
	    log.info("Update user in db - {}", userDto);
	    User user = userRepository.findByEmail(email)
	        .map(u -> {
	          u.setEmail(userDto.getEmail());
	          u.setFirstName(userDto.getFirstName());
	          u.setLastName(userDto.getLastName());
	          return userRepository.save(u);
	        	
	        }).orElseThrow(UserNotFoundException::new);
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
