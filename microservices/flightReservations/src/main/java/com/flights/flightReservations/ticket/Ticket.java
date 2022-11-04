package com.flights.flightReservations.ticket;

import java.math.BigDecimal;
import java.util.List;

import com.flights.flightReservations.bag.Bag;
import com.flights.flightReservations.passenger.Passenger;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity(name="tickets")
public class Ticket {
	@Id
	@GeneratedValue
	private long id;
	
	@OneToOne
	private Passenger passenger;
	
	@OneToMany(mappedBy="ticket")
	private List<Bag> bags;
	
	private BigDecimal totalPrice;
	
	private Integer flightId;

	public Ticket() {
		
	}

	public Ticket(Passenger passenger, BigDecimal totalPrice, Integer flightId) {
		super();
		this.passenger = passenger;
		this.totalPrice = totalPrice;
		this.flightId = flightId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public List<Bag> getBags() {
		return bags;
	}

	public void setBags(List<Bag> bags) {
		this.bags = bags;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getFlightId() {
		return flightId;
	}

	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", passenger=" + passenger + ", totalPrice=" + totalPrice + "]";
	}
}
