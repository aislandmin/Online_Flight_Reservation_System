//Xiamin Guo 301495284 10-29-2025
package com.xiaomin.mvc.jpa.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.xiaomin.mvc.jpa.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	List<Reservation> findByPassengerPassengerId(Long passengerId);
}
