package com.flights.searchflights.flight;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import com.flights.searchflights.airline.Airline;
import com.flights.searchflights.airport.Airport;
import com.flights.searchflights.scale.Scale;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name="flights")
public class Flight {
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private Airline airline;
	
	@ManyToOne
	private Airport origin;
	
	@ManyToOne
	private Airport destination;
	
	private LocalDateTime departure;
	
	private LocalDateTime arrival;
	
	//@OneToMany(mappedBy="flight")
	//private List<Scale> scales;
	
	private String flightNumber;
	
	private Integer flightDurationMinutes;
	
	private BigDecimal price;
	
	private Integer totalSeats;
	
	private Integer availableSeats;
	
	private Integer totalSmallBags;
	
	private Integer availableSmallBags;
	
	private Integer totalCabinBags;
	
	private Integer availableCabinBags;
	
	private Integer totalHoldBags;
	
	private Integer availableHoldBags;

	public Flight() {
		
	}
	
	public Flight(Airline airline, Airport origin, Airport destination, LocalDateTime departure, LocalDateTime arrival, String flightNumber,
			Integer flightDurationMinutes, BigDecimal price, Integer totalSeats, Integer availableSeats, Integer totalSmallBags,
			Integer availableSmallBags, Integer totalCabinBags, Integer availableCabinBags, Integer totalHoldBags,
			Integer availableHoldBags) {
		super();
		this.airline = airline;
		this.origin = origin;
		this.destination = destination;
		this.departure = departure;
		this.arrival = arrival;
		this.flightNumber = flightNumber;
		this.flightDurationMinutes = flightDurationMinutes;
		this.price = price;
		this.totalSeats = totalSeats;
		this.availableSeats = availableSeats;
		this.totalSmallBags = totalSmallBags;
		this.availableSmallBags = availableSmallBags;
		this.totalCabinBags = totalCabinBags;
		this.availableCabinBags = availableCabinBags;
		this.totalHoldBags = totalHoldBags;
		this.availableHoldBags = availableHoldBags;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public Airport getOrigin() {
		return origin;
	}

	public void setOrigin(Airport origin) {
		this.origin = origin;
	}

	public Airport getDestination() {
		return destination;
	}

	public void setDestination(Airport destination) {
		this.destination = destination;
	}

	public LocalDateTime getDeparture() {
		return departure;
	}

	public void setDeparture(LocalDateTime departure) {
		this.departure = departure;
	}

	public LocalDateTime getArrival() {
		return arrival;
	}

	public void setArrival(LocalDateTime arrival) {
		this.arrival = arrival;
	}

	/*public List<Scale> getScales() {
		return scales;
	}

	public void setScales(List<Scale> scales) {
		this.scales = scales;
	}*/

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public Integer getFlightDurationMinutes() {
		return flightDurationMinutes;
	}

	public void setFlightDurationMinutes(Integer flightDurationMinutes) {
		this.flightDurationMinutes = flightDurationMinutes;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(Integer totalSeats) {
		this.totalSeats = totalSeats;
	}

	public Integer getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}

	public Integer getTotalSmallBags() {
		return totalSmallBags;
	}

	public void setTotalSmallBags(Integer totalSmallBags) {
		this.totalSmallBags = totalSmallBags;
	}

	public Integer getAvailableSmallBags() {
		return availableSmallBags;
	}

	public void setAvailableSmallBags(Integer availableSmallBags) {
		this.availableSmallBags = availableSmallBags;
	}

	public Integer getTotalCabinBags() {
		return totalCabinBags;
	}

	public void setTotalCabinBags(Integer totalCabinBags) {
		this.totalCabinBags = totalCabinBags;
	}

	public Integer getAvailableCabinBags() {
		return availableCabinBags;
	}

	public void setAvailableCabinBags(Integer availableCabinBags) {
		this.availableCabinBags = availableCabinBags;
	}

	public Integer getTotalHoldBags() {
		return totalHoldBags;
	}

	public void setTotalHoldBags(Integer totalHoldBags) {
		this.totalHoldBags = totalHoldBags;
	}

	public Integer getAvailableHoldBags() {
		return availableHoldBags;
	}

	public void setAvailableHoldBags(Integer availableHoldBags) {
		this.availableHoldBags = availableHoldBags;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", airline=" + airline + ", origin=" + origin + ", destination=" + destination
				+ ", departure=" + departure + ", arrival=" + arrival + ", flightNumber=" + flightNumber
				+ ", flightDurationMinutes=" + flightDurationMinutes + ", price=" + price + ", totalSeats=" + totalSeats
				+ ", availableSeats=" + availableSeats + ", totalSmallBags=" + totalSmallBags + ", availableSmallBags="
				+ availableSmallBags + ", totalCabinBags=" + totalCabinBags + ", availableCabinBags="
				+ availableCabinBags + ", totalHoldBags=" + totalHoldBags + ", availableHoldBags=" + availableHoldBags
				+ "]";
	}
}
