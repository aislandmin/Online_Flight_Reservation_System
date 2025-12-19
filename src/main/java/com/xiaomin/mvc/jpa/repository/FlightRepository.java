//Xiamin Guo 301495284 10-29-2025
package com.xiaomin.mvc.jpa.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiaomin.mvc.jpa.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
	List<Flight> findByOriginAndDestinationAndDepartureTimeBetween(
			String origin, String destination, LocalDateTime from, LocalDateTime to);
}
