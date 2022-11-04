package com.flights.searchflights.airline;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/airlines")
public class AirlineController {
	private AirlineRepository airlineRepo;
	
	public AirlineController(AirlineRepository airlineRepo) {
		this.airlineRepo = airlineRepo;
	}
	
	@GetMapping
	public List<Airline> retrieveAllUsers(){
		return airlineRepo.findAll();
	}
}
