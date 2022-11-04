package com.flights.searchflights.scale;

import java.util.Calendar;

import com.flights.searchflights.airport.Airport;
import com.flights.searchflights.flight.Flight;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

//@Entity(name="scales")
public class Scale {
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private Flight flight;

	@ManyToOne
	private Airport airport;
	
	@ManyToOne
	private Airport previousAirport;
	
	@ManyToOne
	private Airport nextAirport;
	
	private Calendar departure;
	
	private Calendar arrival;
	
	private Integer flightDurationMinutes;
	
	public Scale() {
		
	}

	public Scale(Airport airport, Airport previousAirport, Airport nextAirport, Calendar departure,
			Calendar arrival, Integer flightDurationMinutes) {
		super();
		this.airport = airport;
		this.previousAirport = previousAirport;
		this.nextAirport = nextAirport;
		this.departure = departure;
		this.arrival = arrival;
		this.flightDurationMinutes = flightDurationMinutes;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Airport getAirport() {
		return airport;
	}

	public void setAirport(Airport airport) {
		this.airport = airport;
	}

	public Airport getPreviousAirport() {
		return previousAirport;
	}

	public void setPreviousAirport(Airport previousAirport) {
		this.previousAirport = previousAirport;
	}

	public Airport getNextAirport() {
		return nextAirport;
	}

	public void setNextAirport(Airport nextAirport) {
		this.nextAirport = nextAirport;
	}

	public Calendar getDeparture() {
		return departure;
	}

	public void setDeparture(Calendar departure) {
		this.departure = departure;
	}

	public Calendar getArrival() {
		return arrival;
	}

	public void setArrival(Calendar arrival) {
		this.arrival = arrival;
	}

	public Integer getFlightDurationMinutes() {
		return flightDurationMinutes;
	}

	public void setFlightDurationMinutes(Integer flightDurationMinutes) {
		this.flightDurationMinutes = flightDurationMinutes;
	}

	@Override
	public String toString() {
		return "Scale [id=" + id + ", airport=" + airport + ", previousAirport=" + previousAirport + ", nextAirport="
				+ nextAirport + ", departure=" + departure + ", arrival=" + arrival + ", flightDurationMinutes="
				+ flightDurationMinutes + "]";
	}
}
