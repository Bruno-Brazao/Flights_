package com.flights.searchflights.flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flights.searchflights.airport.Airport;

public interface FlightRepository extends JpaRepository<Flight, Long> {
	List<Flight> findByOrigin(Optional<Airport> origin);
	List<Flight> findByOriginAndDestinationAndDepartureBetween(Optional<Airport> origin, Optional<Airport> destination, LocalDateTime start, LocalDateTime end);
}
