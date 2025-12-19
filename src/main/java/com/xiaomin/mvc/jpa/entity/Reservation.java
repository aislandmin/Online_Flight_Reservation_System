//Xiamin Guo 301495284 10-29-2025
package com.xiaomin.mvc.jpa.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity 
@Table(name="reservation")
public class Reservation {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="reservation_id")
	private Long reservationId;

	@ManyToOne(optional=false) 
	@JoinColumn(name="passenger_id")
	private Passenger passenger;

	@ManyToOne(optional=false) 
	@JoinColumn(name="flight_id")
	private Flight flight;

	@NotNull 
	@Column(name="booking_date")
	private LocalDate bookingDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull 	
	@Column(name="departure_date")
	private LocalDate departureDate;

	@Min(1) 
	@Column(name="no_of_passengers")
	private int noOfPassengers = 1;

	@NotNull 
	@DecimalMin("0.00") 
	@Column(name="total_price")
	private BigDecimal totalPrice = BigDecimal.valueOf(0.00);

	@Enumerated(EnumType.STRING)
	private Status status = Status.BOOKED;

	public enum Status { PENDING, BOOKED, CANCELLED }

	public Reservation() {
	}

	public Reservation(Long reservationId, Passenger passenger, Flight flight, @NotNull LocalDate bookingDate,
			@NotNull LocalDate departureDate, @Min(1) int noOfPassengers,
			@NotNull @DecimalMin("0.0") BigDecimal totalPrice, Status status) {
		this.reservationId = reservationId;
		this.passenger = passenger;
		this.flight = flight;
		this.bookingDate = bookingDate;
		this.departureDate = departureDate;
		this.noOfPassengers = noOfPassengers;
		this.totalPrice = totalPrice;
		this.status = status;
	}

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public int getNoOfPassengers() {
		return noOfPassengers;
	}

	public void setNoOfPassengers(int noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice.setScale(2);
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
