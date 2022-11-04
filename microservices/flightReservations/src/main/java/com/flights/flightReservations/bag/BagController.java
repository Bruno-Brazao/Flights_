package com.flights.flightReservations.bag;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BagController {
	private BagRepository bagRepo;
	
	public BagController(BagRepository bagRepo) {
		this.bagRepo = bagRepo;
	}
	
	@GetMapping("/bags")
	public List<Bag> retrieveAllBags(){
		return bagRepo.findAll();
	}
}
