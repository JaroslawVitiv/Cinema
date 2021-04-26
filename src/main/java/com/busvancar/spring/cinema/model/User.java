package com.busvancar.spring.cinema.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import com.busvancar.spring.cinema.validator.FieldMatch;
import com.busvancar.spring.cinema.validator.UniqueUsername;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
 
	@Size(min=3, message="{name.should.be.composed}")
	@UniqueUsername(message="Such first name exists in the dataBase")
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	@UniqueUsername(message="Such last name exists in the dataBase")
	private String lastName;
	@Column(name="email", unique = true)
	private String email;
	@NotNull
    @Size(min=6, max=25)
	@Column(name = "password")
	private String password;
	@Transient
	private String confirmPassword;
	@Column(name = "admin")
	private Integer admin;
	
	private Double revenue;
	private Long generatedLong;
	 
	
}
