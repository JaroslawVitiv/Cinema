package com.busvancar.spring.cinema.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.busvancar.spring.cinema.repository.UserRepository;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String>{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return !userRepository.findByEmail(email).isPresent();
	}
	

}
