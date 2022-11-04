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

@SpringBootTest
@AutoConfigureMockMvc
class AirlineTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private AirlineController resource;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(resource).isNotNull();
	}

	@Test
	public void shouldGetAllAirlines() throws Exception {
		mockMvc.perform(get("/airlines"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].name", is("Ryanair")))
			.andExpect(jsonPath("$[1].name", is("Easyjet")));;
	}
}
