//Xiamin Guo 301495284 10-29-2025
package com.xiaomin.mvc.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiaomin.mvc.jpa.entity.Passenger;
import com.xiaomin.mvc.jpa.service.PassengerService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/passengers")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;
    
    // Profile page
    @GetMapping("/profile/{id}")
    public String viewProfile(@PathVariable Long id, Model model) {
        Passenger passenger = passengerService.getPassengerById(id);
        model.addAttribute("passenger", passenger);
        return "passenger_profile";
    }

   
    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute Passenger passenger, RedirectAttributes redirectAttributes) {
        try {
            Passenger existingPassenger = passengerService.getPassengerById(passenger.getPassengerId());
            
            existingPassenger.setEmail(passenger.getEmail());
            existingPassenger.setFirstname(passenger.getFirstname());
            existingPassenger.setLastname(passenger.getLastname());
            existingPassenger.setAddress(passenger.getAddress());
            existingPassenger.setCity(passenger.getCity());
            existingPassenger.setPostalCode(passenger.getPostalCode());
            
            passengerService.registerPassenger(existingPassenger); // save updates

            redirectAttributes.addFlashAttribute("successMessage", "Profile updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update profile. Please try again.");
        }

        return "redirect:/passengers/profile/" + passenger.getPassengerId();

    }

}

