package com.busvancar.spring.cinema.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.busvancar.spring.cinema.validator.UniqueUsername;

@Entity
@Table(name="user")
public class Employee implements Serializable {
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public Integer getAdmin() {
		return admin;
	}
	public void setAdmin(Integer admin) {
		this.admin = admin;
	}
	public Double getRevenue() {
		return revenue;
	}
	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}
	public Long getGeneratedLong() {
		return generatedLong;
	}
	public void setGeneratedLong(Long generatedLong) {
		this.generatedLong = generatedLong;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Employee() {
		
	}
	
	@Override
	public String toString() {
		return "Employee: "+firstName+"-->"+"lastName";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
 
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	@Column(name="email")
	private String email;
	@Column(name = "password")
	private String password;
	@Transient
	private String confirmPassword;
	@Column(name = "admin")
	private Integer admin;
	
	private Double revenue;
	private Long generatedLong;
	
	
	

}
