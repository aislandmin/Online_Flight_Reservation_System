//Xiamin Guo 301495284 10-29-2025
package com.xiaomin.mvc.jpa.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity 
@Table(name="flight")
public class Flight {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="flight_id")
	private Long flightId;

	@NotBlank 
	@Column(name="airline_name")
	private String airlineName;
	
	@NotNull 
	@Column(name="departure_time")
	private LocalDateTime departureTime;
	@NotNull 
	@Column(name="arrival_time")
	private LocalDateTime arrivalTime;

	@NotBlank 
	private String origin;
	@NotBlank 
	private String destination;

	@NotNull 
	@DecimalMin("0.0") 
	private BigDecimal price;

	public Flight() {
	}

	public Flight(Long flightId, @NotBlank String airlineName, @NotNull LocalDateTime departureTime,
			@NotNull LocalDateTime arrivalTime, @NotBlank String origin, @NotBlank String destination,
			@NotNull @DecimalMin("0.0") BigDecimal price) {
		this.flightId = flightId;
		this.airlineName = airlineName;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.origin = origin;
		this.destination = destination;
		this.price = price;
	}

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
