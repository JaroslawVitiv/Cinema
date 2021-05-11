package com.busvancar.spring.cinema.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.busvancar.spring.cinema.model.Employee;
import com.busvancar.spring.cinema.service.EmployeeService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/epi")
public class EmployeeController {
	
	@Autowired
	EmployeeService service;
	
	@PostMapping("/post")
	public Employee saveEmployee(@RequestBody Employee employee) {
		service.saveEmployee(employee);
		return employee;	
	}

	@GetMapping("/list")
	public List<Employee> list(){
		return service.getAllEmployee();
	}
	
	@GetMapping("/list/{email}")
	public Employee getByEmail(@PathVariable String email){
		return service.getByEmail(email);
	}
	
	@DeleteMapping("/delete/{email}")
	public void deleteEmployee(@PathVariable(value="email") String email) {
		service.deleteEmployee(email);
	}
	
	@PatchMapping("/update/{email}")
	public Employee updateUser(@PathVariable String email, @RequestBody Employee employee) {
		service.saveOrUpdate(email, employee);
		return employee;
	}
}
