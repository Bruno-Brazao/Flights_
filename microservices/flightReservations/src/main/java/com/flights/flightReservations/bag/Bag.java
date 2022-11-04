package com.flights.flightReservations.bag;

import java.math.BigDecimal;

import com.flights.flightReservations.ticket.Ticket;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name="bags")
public class Bag {
	@Id
	@GeneratedValue
	private long id;
	
	private String type;
	
	@ManyToOne
	private Ticket ticket; 
	
	private BigDecimal price;

	public Bag() {

	}

	public Bag(String type, Ticket ticket, BigDecimal price) {
		super();
		this.type = type;
		this.ticket = ticket;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Bag [id=" + id + ", type=" + type + ", ticket=" + ticket + ", price=" + price + "]";
	}
}
