package com.yk.greeting.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.yk.greeting.model.UserRequestDTO;
import com.yk.greeting.service.UserService;
import com.yk.greeting.service.UserVisitService;

@RunWith(SpringRunner.class)
@WebMvcTest(GreetingController.class)
public class GreetingControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	UserService userService;

	@MockBean
	UserVisitService userVisitService;

	/**
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() {
		UserRequestDTO user = new UserRequestDTO("FirstName", "LastName");
		when(userVisitService.createUserVisit(user)).thenReturn(true);
		UserRequestDTO user1 = new UserRequestDTO("FirstName", "");
		when(userVisitService.createUserVisit(user1)).thenReturn(false);
	}

	@Test
	public void greeting_whenNoUser_returnWelcome() throws Exception {
		UserRequestDTO user1 = new UserRequestDTO("FirstName", "");

		this.mockMvc
				.perform(post("/greeting").contentType(TestUtil.APPLICATION_JSON_UTF8)
						.content(TestUtil.convertObjectToJsonBytes(user1)))
				.andExpect(status().isOk()).andExpect(content().string(containsString("Welcome,  FirstName")));

	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void greeting_whenExistingUser_returnWelcomeBack() throws Exception {
		UserRequestDTO user = new UserRequestDTO("FirstName", "LastName");

		this.mockMvc
				.perform(post("/greeting").contentType(TestUtil.APPLICATION_JSON_UTF8)
						.content(TestUtil.convertObjectToJsonBytes(user)))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Welcome, back FirstName LastName")));
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void greeting_whenEmpty_returnWelcome() throws Exception {
		UserRequestDTO user = new UserRequestDTO("", "");
		this.mockMvc
				.perform(post("/greeting").contentType(TestUtil.APPLICATION_JSON_UTF8)
						.content(TestUtil.convertObjectToJsonBytes(user)))
				.andExpect(status().isOk()).andExpect(content().string(containsString("Welcome,    !")));
	}

}
