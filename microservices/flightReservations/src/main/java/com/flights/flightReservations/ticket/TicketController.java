package com.flights.flightReservations.ticket;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flights.flightReservations.bag.Bag;
import com.flights.flightReservations.bag.BagRepository;
import com.flights.flightReservations.passenger.Passenger;
import com.flights.flightReservations.passenger.PassengerRepository;

import org.json.JSONException;
import org.json.JSONObject;

@RestController
@RequestMapping("/tickets")
public class TicketController {
	private TicketRepository ticketRepo;
	private PassengerRepository passengerRepo;
	private BagRepository bagRepo;
	
	public TicketController(TicketRepository ticketRepo, PassengerRepository passengerRepo, BagRepository bagRepo) {
		this.ticketRepo = ticketRepo;
		this.passengerRepo = passengerRepo;
		this.bagRepo = bagRepo;
	}
	
	@GetMapping
	public List<Ticket> retrieveAllTickets(){
		return ticketRepo.findAll();
	}
	
	@PostMapping("/book")
	public Optional<Ticket> bookTicket(@RequestBody String json) throws JSONException{
		JSONObject req = new JSONObject(json);
		JSONObject passenger = req.getJSONObject("passenger");
		Passenger createdPassanger = passengerRepo.save(new Passenger(passenger.getString("name"), passenger.getString("surname"), passenger.getString("nationality"), passenger.getString("passport"), passenger.getString("age")));
		Ticket newTicket = ticketRepo.save(new Ticket(createdPassanger, new BigDecimal(req.getDouble("price")), req.getInt("flightId")));

		JSONObject bags = req.getJSONObject("bags");
		for (int i = 0; i < bags.getJSONObject("small").getInt("quantity"); i++)
			bagRepo.save(new Bag("small", newTicket, new BigDecimal(bags.getJSONObject("small").getDouble("price"))));
		for (int i = 0; i < bags.getJSONObject("cabin").getInt("quantity"); i++)
			bagRepo.save(new Bag("cabin", newTicket, new BigDecimal(bags.getJSONObject("cabin").getDouble("price"))));
		for (int i = 0; i < bags.getJSONObject("hold").getInt("quantity"); i++)
			bagRepo.save(new Bag("hold", newTicket, new BigDecimal(bags.getJSONObject("hold").getDouble("price"))));

		return ticketRepo.findById(newTicket.getId());
	}
}
