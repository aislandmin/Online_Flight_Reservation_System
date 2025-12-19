package com.xiaomin.mvc.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
//Xiamin Guo 301495284 10-29-2025
import jakarta.validation.constraints.Size;

@Entity
@Table(name="passenger")
public class Passenger {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="passenger_id")
	private Long passengerId;

	@Email 
	@NotBlank 
	@Column(unique = true, length = 120)
	private String email;

	@NotBlank 
	@Column(name = "password_hash", length = 100)
	private String passwordHash; // store hash only
	
	@Transient // raw password, not persisted in DB
	private String password;

	@NotBlank 
	@Size(max=60)
	private String firstname;

	@NotBlank 
	@Size(max=60)
	private String lastname;

	@Size(max=255)
	private String address;

	@Size(max=80)
	private String city;

	@Size(max=20) 
	@Column(name="postal_code")
	private String postalCode;

	public Passenger() {
	}

	public Passenger(Long passengerId, @Email @NotBlank String email, @NotBlank String passwordHash,
			@NotBlank @Size(max = 60) String firstname, @NotBlank @Size(max = 60) String lastname,
			@Size(max = 255) String address, @Size(max = 80) String city, @Size(max = 20) String postalCode) {
		this.passengerId = passengerId;
		this.email = email;
		this.passwordHash = passwordHash;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.city = city;
		this.postalCode = postalCode;
	}

	public Long getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(Long passengerId) {
		this.passengerId = passengerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
}
