package com.busvancar.spring.cinema.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.busvancar.spring.cinema.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	Optional <Employee> findByEmail(String email);

	void deleteByEmail(String email);

	boolean existsByEmail(String email);

}
