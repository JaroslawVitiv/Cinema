package com.busvancar.spring.cinema.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.busvancar.spring.cinema.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional <User> findByEmail(String email);

	boolean existsByEmail(String email);

}
