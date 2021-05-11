package com.busvancar.spring.cinema.api;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.busvancar.spring.cinema.controller.model.UserModel;
import com.busvancar.spring.cinema.dto.UserDto;
import com.busvancar.spring.cinema.model.User;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "User Management Api") 
@RequestMapping("/api/v1/users")
public interface UserApi{
	
	@ApiOperation("Get user from database by Email" )
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/get/{email}", method = RequestMethod.GET)
	public @ResponseBody UserModel findUser(@PathVariable String email);

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/post")
	@ResponseBody
	public UserModel insertUser(@RequestBody UserDto userDto);

	@ResponseStatus(HttpStatus.OK)
	@PatchMapping(value = "/patch/{email}")
	public UserModel updateUser(@PathVariable String email, @RequestBody UserDto userDto);

	@RequestMapping(value = "/delete/{email}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable String email);

	@PostMapping("/login/{name}")
	public long login(@PathVariable String name, @RequestBody UserDto userDto, HttpSession session);
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/all")
	public List<User> listAllUsers();
		
	
	
}
