package com.flights.searchflights.airport;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name="airports")
public class Airport {
	@Id
	@GeneratedValue
	private long id;

	private String name;
	
	
	public Airport() {
		
	}

	public Airport(String name) {
		super();
		this.name = name;
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

	@Override
	public String toString() {
		return "Airport [id=" + id + ", name=" + name + "]";
	}
}
