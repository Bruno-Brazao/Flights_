package com.flights.flightReservations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.flights.flightReservations.ticket.TicketController;

@SpringBootTest
@AutoConfigureMockMvc
class TicketTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private TicketController resource;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(resource).isNotNull();
	}

	@Test
	public void shouldCreateNewTicket() throws Exception {
		mockMvc.perform(post("/tickets/book","{\"passenger\":{\"name\":\"Namee\",\"surname\":\"Surnamee\",\"nationality\":\"Portuguese\",\"passport\":\"123456789\",\"age\":\">9\"},\"bags\":{\"small\":{\"quantity\":1,\"price\":0},\"cabin\":{\"quantity\":0,\"price\":14.99},\"hold\":{\"quantity\":0,\"price\":50.5}},\"price\":\"65.00\",\"flightId\":1}"))
			.andExpect(status().isOk());
	}
}
