package com.yk.greeting.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.yk.greeting.model.UserRequestDTO;
import com.yk.greeting.service.UserVisitAuditService;
@RunWith(MockitoJUnitRunner.class)
public class GreetingControllerTest {
	@Mock
	UserVisitAuditService userVisitService;
	@InjectMocks
	GreetingController a = new GreetingController() ;
	
	@Test
	public void test_WelcomeFirstNameLastName() {
		UserRequestDTO userRequestDTO = new UserRequestDTO("FirstName" , "LastName"); 
		when(userVisitService.manageUserVisit(userRequestDTO)).thenReturn(false);
		assertEquals("Welcome FirstName, LastName !", a.greeting(userRequestDTO));
	}
	
	@Test
	public void test_WelcomeBackFirstNameLastName() {
		UserRequestDTO userRequestDTO = new UserRequestDTO("FirstName" , "LastName"); 
		when(userVisitService.manageUserVisit(userRequestDTO)).thenReturn(true);
		assertEquals("Welcome back FirstName, LastName !", a.greeting(userRequestDTO));
	}
	
	@Test
	public void test_WelcomeBackFirstName() {
		UserRequestDTO userRequestDTO = new UserRequestDTO("FirstName" , ""); 
		when(userVisitService.manageUserVisit(userRequestDTO)).thenReturn(true);
		assertEquals("Welcome back FirstName,  !", a.greeting(userRequestDTO));
	}
	
	@Test
	public void test_WelcomeFirstName() {
		UserRequestDTO userRequestDTO = new UserRequestDTO("FirstName" , ""); 
		when(userVisitService.manageUserVisit(userRequestDTO)).thenReturn(false);
		assertEquals("Welcome FirstName,  !", a.greeting(userRequestDTO));
	}

}
