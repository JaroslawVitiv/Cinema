package com.busvancar.spring.cinema.service;

import java.util.List;

import com.busvancar.spring.cinema.model.Employee;


public interface EmployeeService {
	
	public List<Employee> getAllEmployee();
	
	public Employee getByEmail(String email);
	
	public void deleteEmployee(String email);

	public Employee updateUser(String email, Employee employee);

	Employee saveOrUpdate(String email, Employee employee);

	public void saveEmployee(Employee employee);

}
