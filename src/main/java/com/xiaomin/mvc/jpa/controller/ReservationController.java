//Xiamin Guo 301495284 10-29-2025 
package com.xiaomin.mvc.jpa.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiaomin.mvc.jpa.entity.Flight;
import com.xiaomin.mvc.jpa.entity.Passenger;
import com.xiaomin.mvc.jpa.entity.Reservation;
import com.xiaomin.mvc.jpa.service.FlightService;
import com.xiaomin.mvc.jpa.service.PassengerService;
import com.xiaomin.mvc.jpa.service.ReservationService;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private PassengerService passengerService;

    // List reservations for a passenger
    @GetMapping("/history/{passengerId}")
    public String ViewReservationHistory(@PathVariable Long passengerId, Model model) {
        Passenger passenger = passengerService.getPassengerById(passengerId);
        List<Reservation> reservations = reservationService.getReservationsByPassenger(passengerId);
        
        model.addAttribute("passenger", passenger);
        model.addAttribute("reservations", reservations);
        return "reservation_history"; // reservation_history.html
    }
    
    @GetMapping("/book/{passengerId}")
    public String showReservationPage(@PathVariable Long passengerId, Model model) {
        Passenger passenger = passengerService.getPassengerById(passengerId);
        List<Flight> flights = flightService.getAllFlights();
        Reservation newReservation = new Reservation();

        model.addAttribute("passenger", passenger);
        model.addAttribute("flights", flights);
        newReservation.setPassenger(passenger);
        model.addAttribute("reservation", newReservation); // for new booking form
        return "reservation_page"; // reservation_page.html
    }
       
    @PostMapping("/book")
    public String bookFlight(@ModelAttribute Reservation reservation, Model model) {
        // Save the reservation temporarily (maybe with status=PENDING)
        Reservation savedReservation = reservationService.savePendingReservation(reservation);

        model.addAttribute("reservation", savedReservation);
        
        return "payment_page";
    }

    @PostMapping("/pay")
    public String goToPaymentPage(@RequestParam Long reservationId, Model model) {
        Reservation reservation = reservationService.getReservationById(reservationId);
        model.addAttribute("reservation", reservation);

        return "payment_page";
    }
    
    @PostMapping("/cancel/{id}")
    public String cancelReservation(@PathVariable("id") Long reservationId) {
        Reservation reservation = reservationService.getReservationById(reservationId);

        reservationService.cancelReservation(reservation);    //call cancel method in service

        return "redirect:/reservations/history/" + reservation.getPassenger().getPassengerId();
    }
  
    // Show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long reservationId, Model model) {
        Reservation reservation = reservationService.getReservationById(reservationId);

        model.addAttribute("reservation", reservation);
        
        return "reservation_edit"; 
    }

    // Update reservation
    @PostMapping("/update")
    public String updateReservation(@ModelAttribute Reservation reservation) {
        Reservation existingReservation = reservationService.getReservationById(reservation.getReservationId());
        
        existingReservation.setDepartureDate(reservation.getDepartureDate());
        existingReservation.setNoOfPassengers(reservation.getNoOfPassengers());
        existingReservation.setTotalPrice(reservation.getTotalPrice());
        
        reservationService.updateReservation(existingReservation); // saves updated info
        
        return "redirect:/reservations/history/" + reservation.getPassenger().getPassengerId();
    }
    
    @PostMapping("/confirmation") 
    public String confirmPayment(@RequestParam Long reservationId, 
                                 @RequestParam String paymentMethod, 
                                 Model model) {
        Reservation reservation = reservationService.getReservationById(reservationId);
        reservationService.bookReservation(reservation);//Status: BOOKED

        model.addAttribute("reservation", reservation);
        model.addAttribute("paymentMethod", paymentMethod);
        
        return "payment_confirmation"; 
    }

}

