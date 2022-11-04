package com.flights.searchflights.flight;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flights.searchflights.airport.Airport;
import com.flights.searchflights.airport.AirportRepository;

@RestController
@RequestMapping("/flights")
public class FlightController {
	private FlightRepository flightRepo;
	private AirportRepository airportRepo;
	
	public FlightController(FlightRepository flightRepo, AirportRepository airportRepo) {
		this.flightRepo = flightRepo;
		this.airportRepo = airportRepo;
	}
	
	@GetMapping
	public List<Flight> retrieveAllFlights(){
		return flightRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Flight> retrieveFlightByid(@PathVariable long id){
		return flightRepo.findById(id);
	}
	
	@GetMapping("/search")
	public List<Flight> retrieveFlights(@RequestParam Map<String, String> params){
		List<Flight> res = new ArrayList<>();
		
		if (params.containsKey("origin") && params.containsKey("destination") && params.containsKey("departure")) {
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			LocalDateTime departure = LocalDateTime.parse(params.get("departure")+" "+(now.getHour()<10?"0"+now.getHour():now.getHour())+":"+(now.getMinute()<10?"0"+now.getMinute():now.getMinute()), formatter);
			departure.plusSeconds(30);
			if(departure.toLocalDate().isBefore(now.toLocalDate()))
				return res;
			int diff = 3 - departure.toLocalDate().compareTo(now.toLocalDate());
			//calculate interval of dates to search (3 days before and after the desired departure)
			LocalDateTime start = departure.minusDays(3 - (diff > 0 ? diff : 0));
			LocalDateTime end = departure.plusDays(3 + (diff > 0 ? diff : 0));
			
			//get origin airport and destination airport
			Optional<Airport> origin = airportRepo.findById(Long.parseLong(params.get("origin")));
			Optional<Airport> destination = airportRepo.findById(Long.parseLong(params.get("destination")));
			//get all flights from origin to destination
			List<Flight> flights = flightRepo.findByOriginAndDestinationAndDepartureBetween(origin, destination, start, end);
			//loop flights and apply possible filters to the search
			for (Flight flight : flights) {
				//filter by airlines if possible
				if(params.containsKey("airlines")) {
					String[] airlines = params.get("airlines").split("-");
					boolean shouldContinue = false;
					for (String airline : airlines) {
						if(flight.getAirline().getName().equalsIgnoreCase(airline)){
							shouldContinue = true;
						}
					}
					if(!shouldContinue)
						continue;
				}
				//filter by departure time if possible
				if(params.containsKey("departuretimes")) {
					String[] departureTimes = params.get("departuretimes").split("-");
					if (!(Integer.parseInt(departureTimes[0]) <= flight.getDeparture().getHour() && Integer.parseInt(departureTimes[1]) > flight.getDeparture().getHour()))
						continue;
				}
				
				res.add(flight);
			}
		}
		return res;
	}
}
