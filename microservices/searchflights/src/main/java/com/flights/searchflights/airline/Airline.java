package com.flights.searchflights.airline;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name="airlines")
public class Airline {
	@Id
	@GeneratedValue
	private long id;

	private String name;

	private BigDecimal holdBagPrice;
	
	private BigDecimal cabinBagPrice;
	
	private BigDecimal smallBagPrice;

	private BigDecimal ageLessThan2Price;
	
	private BigDecimal ageLessThan9Price;
	
	private BigDecimal ageMoreThan9Price;
	
	public Airline() {
		
	}

	public Airline(String name, BigDecimal holdBagPrice, BigDecimal cabinBagPrice, BigDecimal smallBagPrice,
			BigDecimal ageLessThan2Price, BigDecimal ageLessThan9Price, BigDecimal ageMoreThan9Price) {
		super();
		this.name = name;
		this.holdBagPrice = holdBagPrice;
		this.cabinBagPrice = cabinBagPrice;
		this.smallBagPrice = smallBagPrice;
		this.ageLessThan2Price = ageLessThan2Price;
		this.ageLessThan9Price = ageLessThan9Price;
		this.ageMoreThan9Price = ageMoreThan9Price;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getHoldBagPrice() {
		return holdBagPrice;
	}

	public void setHoldBagPrice(BigDecimal holdBagPrice) {
		this.holdBagPrice = holdBagPrice;
	}

	public BigDecimal getCabinBagPrice() {
		return cabinBagPrice;
	}

	public void setCabinBagPrice(BigDecimal cabinBagPrice) {
		this.cabinBagPrice = cabinBagPrice;
	}

	public BigDecimal getSmallBagPrice() {
		return smallBagPrice;
	}

	public void setSmallBagPrice(BigDecimal smallBagPrice) {
		this.smallBagPrice = smallBagPrice;
	}

	public BigDecimal getAgeLessThan2Price() {
		return ageLessThan2Price;
	}

	public void setAgeLessThan2Price(BigDecimal ageLessThan2Price) {
		this.ageLessThan2Price = ageLessThan2Price;
	}

	BigDecimal getAgeLessThan9Price() {
		return ageLessThan9Price;
	}

	public void setAgeLessThan9Price(BigDecimal ageLessThan9Price) {
		this.ageLessThan9Price = ageLessThan9Price;
	}

	public BigDecimal getAgeMoreThan9Price() {
		return ageMoreThan9Price;
	}

	public void setAgeMoreThan9Price(BigDecimal ageMoreThan9Price) {
		this.ageMoreThan9Price = ageMoreThan9Price;
	}

	@Override
	public String toString() {
		return "Airline [id=" + id + ", name=" + name + ", holdBagPrice=" + holdBagPrice + ", cabinBagPrice="
				+ cabinBagPrice + ", smallBagPrice=" + smallBagPrice + ", ageLessThan2Price=" + ageLessThan2Price
				+ ", ageLessThan9Price=" + ageLessThan9Price + ", ageMoreThan9Price=" + ageMoreThan9Price + "]";
	}
}
