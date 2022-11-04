package com.flights.flightReservations.passenger;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name="passengers")
public class Passenger {
	@Id
	@GeneratedValue
	private long id;

	private String name;
	
	private String surname;
	
	private String nationality;
	
	private String passport;
	
	private String age;

	public Passenger() {
		
	}
	
	public Passenger(String name, String surname, String nationality, String passport, String age) {
		super();
		this.name = name;
		this.surname = surname;
		this.nationality = nationality;
		this.passport = passport;
		this.age = age;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Passenger [id=" + id + ", name=" + name + ", surname=" + surname + ", nationality=" + nationality
				+ ", passport=" + passport + ", age=" + age + "]";
	}
}
