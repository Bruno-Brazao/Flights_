package com.flights.searchflights;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.flights.searchflights.airline.AirlineController;
import com.flights.searchflights.flight.FlightController;

@SpringBootTest
@AutoConfigureMockMvc
class FlightTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private FlightController resource;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(resource).isNotNull();
	}

	@Test
	public void shouldGetAllFlightWithId1() throws Exception {
		mockMvc.perform(get("/flights/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$airline.name", is("Easyjet")))
			.andExpect(jsonPath("$flightNumber", is("E1")));
	}
	
	@Test
	public void shouldGetAllFlightForQuery() throws Exception {
		mockMvc.perform(get("/flights/search?departure=2022-11-20&origin=1&destination=2&airlines=easyjet-ryanair"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].id", is(1)))
			.andExpect(jsonPath("$[0].origin.id", is(1)))
			.andExpect(jsonPath("$[0].destination.id", is(2)));
	}
}
