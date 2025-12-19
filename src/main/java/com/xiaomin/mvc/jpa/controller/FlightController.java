//Xiamin Guo 301495284 10-29-2025
package com.xiaomin.mvc.jpa.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiaomin.mvc.jpa.entity.Flight;
import com.xiaomin.mvc.jpa.service.FlightService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    // Display all flights
    @GetMapping
    public String listFlights(Model model) {
        model.addAttribute("flights", flightService.getAllFlights());
        return "flight-list"; // Thymeleaf template
    }

    // Show search form
    @GetMapping("/search")
    public String showSearchForm() {
        return "flight-search"; // Thymeleaf template
    }

    // Search flights
    @GetMapping("/search/results")
    public String searchFlights(@RequestParam String origin,
                                @RequestParam String destination,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
                                Model model) {
        List<Flight> flights = flightService.searchFlights(origin, destination, from, to);
        model.addAttribute("flights", flights);
        return "flight-list";
    }

    // Admin add flight form
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("flight", new Flight());
        return "flight-form";
    }

    // Save new flight
    @PostMapping("/save")
    public String saveFlight(@ModelAttribute Flight flight) {
        flightService.saveFlight(flight);
        return "redirect:/flights";
    }

    // Delete flight
    @GetMapping("/delete/{id}")
    public String deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return "redirect:/flights";
    }
}
