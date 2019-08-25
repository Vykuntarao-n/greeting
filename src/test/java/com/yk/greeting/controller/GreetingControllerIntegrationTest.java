package com.yk.greeting.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.yk.greeting.model.UserRequestDTO;
import com.yk.greeting.service.UserVisitAuditService;

@RunWith(SpringRunner.class)
@WebMvcTest(GreetingController.class)
public class GreetingControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	UserVisitAuditService userVisitService;

	

	@Test
	public void greeting_whenNoUser_returnWelcome() throws Exception {
		UserRequestDTO user1 = new UserRequestDTO("FirstName", "");
		when(userVisitService.manageUserVisit(user1)).thenReturn(false);
		this.mockMvc
				.perform(post("/greeting").contentType(TestUtil.APPLICATION_JSON_UTF8)
						.content(TestUtil.convertObjectToJsonBytes(user1)))
				.andExpect(status().isOk()).andExpect(content().string(containsString("Welcome FirstName")));

	}


	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void greeting_whenEmpty_returnWelcome() throws Exception {
		UserRequestDTO user = new UserRequestDTO("", "");
		when(userVisitService.manageUserVisit(user)).thenReturn(false);
		this.mockMvc
				.perform(post("/greeting").contentType(TestUtil.APPLICATION_JSON_UTF8)
						.content(TestUtil.convertObjectToJsonBytes(user)))
				.andExpect(status().isOk()).andExpect(content().string(containsString("Welcome !")));
	}

}
