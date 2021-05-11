package com.busvancar.spring.cinema.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.busvancar.spring.cinema.dto.UserDto;
import com.busvancar.spring.cinema.exception.UserNotFoundException;
import com.busvancar.spring.cinema.model.Employee;
import com.busvancar.spring.cinema.model.User;
import com.busvancar.spring.cinema.repository.EmployeeRepository;
import com.busvancar.spring.cinema.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Resource
	EmployeeRepository repository;
	
	@Override
	public List<Employee> getAllEmployee() {
		return (List<Employee>) repository.findAll();
	}

	@Override
	public Employee getByEmail(String email) {
		return repository.findByEmail(email).get();
	}

	
	  @Override
	  @Transactional
	  public Employee saveOrUpdate(String email, Employee employee) {
	    Employee empl = repository.findByEmail(email)
	        .map(u -> {
	          u.setEmail(employee.getEmail());
	          u.setFirstName(employee.getFirstName());
	          u.setLastName(employee.getLastName());
	          return repository.save(u);
	        	
	        }).orElseThrow(UserNotFoundException::new);
	    return empl;
	  }
	
	

	@Override
	public void deleteEmployee(String email) {
		repository.deleteByEmail(email);		
	}

	@Override
	public Employee updateUser(String email, Employee employee) {
		if(!repository.existsByEmail(email)) {
			throw new UserNotFoundException();
		}
		return repository.save(employee);	
	}

	@Override
	public void saveEmployee(Employee employee) {
		 repository.save(employee);		
	}
	
	

}
