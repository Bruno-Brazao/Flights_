package com.flights.searchflights.airport;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flights.searchflights.flight.Flight;
import com.flights.searchflights.flight.FlightRepository;

@RestController
@RequestMapping("/airports")
public class AirportController {
	private AirportRepository airportRepo;
	private FlightRepository flightRepo;
	
	public AirportController(AirportRepository airportRepo, FlightRepository flightRepo) {
		this.airportRepo = airportRepo;
		this.flightRepo = flightRepo;
	}
	
	@GetMapping
	public List<Airport> retrieveAllUsers(){
		return airportRepo.findAll();
	}
	
	@GetMapping("/{id}/destinations")
	public List<Airport> retrievePossibleDestinations(@PathVariable long id){
		List<Airport> airs = new ArrayList<>();
		for (Flight airport : flightRepo.findByOrigin(airportRepo.findById(id))) {
			if (!airs.contains(airport.getDestination()))
				airs.add(airport.getDestination());
		};
		return airs;
	}
}
