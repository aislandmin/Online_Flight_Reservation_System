//Xiamin Guo 301495284 10-29-2025
package com.xiaomin.mvc.jpa.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaomin.mvc.jpa.entity.Reservation;
import com.xiaomin.mvc.jpa.repository.ReservationRepository;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    // after payment, status becomes BOOKED from PENDING 
    public Reservation bookReservation(Reservation reservation) {
        reservation.setStatus(Reservation.Status.BOOKED);
        return reservationRepository.save(reservation);
    }

    public Reservation cancelReservation(Reservation reservation) {
        reservation.setStatus(Reservation.Status.CANCELLED);
        return reservationRepository.save(reservation);
    }
    
    public List<Reservation> getReservationsByPassenger(Long passengerId) {
        return reservationRepository.findByPassengerPassengerId(passengerId);
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    // for reservation waiting for payment
	public Reservation savePendingReservation(Reservation reservation) {
        reservation.setBookingDate(LocalDate.now());
        reservation.setStatus(Reservation.Status.PENDING);
        return reservationRepository.save(reservation);
	}

	public Reservation updateReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
	}
}
