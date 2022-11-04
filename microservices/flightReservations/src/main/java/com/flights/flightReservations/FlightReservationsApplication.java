package com.flights.flightReservations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FlightReservationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightReservationsApplication.class, args);
	}

}
