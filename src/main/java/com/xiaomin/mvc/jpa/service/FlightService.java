//Xiamin Guo 301495284 10-29-2025
package com.xiaomin.mvc.jpa.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaomin.mvc.jpa.entity.Flight;
import com.xiaomin.mvc.jpa.repository.FlightRepository;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> searchFlights(String origin, String destination, LocalDateTime from, LocalDateTime to) {
        return flightRepository.findByOriginAndDestinationAndDepartureTimeBetween(origin, destination, from, to);
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
    }

    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    // Get all flights
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
}

