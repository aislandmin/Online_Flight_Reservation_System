//Xiamin Guo 301495284 10-29-2025
package com.xiaomin.mvc.jpa.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.xiaomin.mvc.jpa.entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
	Optional<Passenger> findByEmail(String email);
}
