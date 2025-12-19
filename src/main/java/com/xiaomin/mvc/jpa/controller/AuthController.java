//Xiamin Guo 301495284 10-29-2025
package com.xiaomin.mvc.jpa.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.xiaomin.mvc.jpa.entity.Passenger;
import com.xiaomin.mvc.jpa.service.PassengerService;
import com.xiaomin.mvc.jpa.utils.PasswordUtils;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@Controller
public class AuthController {

    @Autowired
    private PassengerService passengerService;

    // Home page with login & signup links
    @GetMapping("/")
    public String home() {
        return "index"; // index.html
    }

    // Registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("passenger", new Passenger());
        return "register"; // register.html
    }

    // Handle registration
    @PostMapping("/register")
    public String registerPassenger(@ModelAttribute Passenger passenger, Model model) {
        Optional<Passenger> existing = passengerService.findByEmail(passenger.getEmail());
        if (existing.isPresent()) {
            model.addAttribute("error", "Email already registered");
            return "register";
        }

        // Hash the password before saving
        passenger.setPasswordHash(PasswordUtils.hashPassword(passenger.getPassword()));

        // Save passenger
        passengerService.registerPassenger(passenger);

        model.addAttribute("message", "Registration successful! Please login.");
        return "login"; // redirect to login page
    }

    // Login form
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("passenger", new Passenger());
        return "login"; // login.html
    }

    // Handle login
    @PostMapping("/login")
    public String loginPassenger(@ModelAttribute Passenger passenger, Model model, HttpSession session) {
        Optional<Passenger> existing = passengerService.findByEmail(passenger.getEmail());
        if (existing.isPresent()) {
            // Hash the raw password entered at login
            String hashedPassword = PasswordUtils.hashPassword(passenger.getPassword());

            // Compare with stored hashed password
            if (hashedPassword.equals(existing.get().getPasswordHash())) {
                // Successful login
                session.setAttribute("passenger", existing.get());
                return "redirect:/reservations/book/" + existing.get().getPassengerId();
            }
        } 
        
        // Failed login
        model.addAttribute("error", "Invalid email or password");
        return "login";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
