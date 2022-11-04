package com.flights.flightReservations.passenger;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PassengerController {
	private PassengerRepository passengerRepo;
	
	public PassengerController(PassengerRepository passengerRepo) {
		this.passengerRepo = passengerRepo;
	}
	
	@GetMapping("/passengers")
	public List<Passenger> retrieveAllPassengers(){
		return passengerRepo.findAll();
	}
}
