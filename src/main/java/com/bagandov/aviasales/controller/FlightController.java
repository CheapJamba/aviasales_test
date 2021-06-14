package com.bagandov.aviasales.controller;

import com.bagandov.aviasales.model.Flight;
import com.bagandov.aviasales.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("flights")
public class FlightController {

    private FlightRepository flightRepository;

    @Autowired
    public FlightController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @GetMapping("/{id}")
    public Flight getFlightById(@PathVariable("id") int targetId) {
        return flightRepository.findFlightById(targetId);
    }
}
