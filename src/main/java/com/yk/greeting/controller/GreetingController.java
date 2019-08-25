package com.yk.greeting.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yk.greeting.config.Greeting;
import com.yk.greeting.model.UserRequestDTO;
import com.yk.greeting.service.UserVisitService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author Vykuntarao
 *
 */
@RestController
public class GreetingController{

	private static final Logger log = LoggerFactory.getLogger(GreetingController.class);

	@Autowired
	UserVisitService userVisitService;

	/**
	 * This will greet the user with the Welcome message with the name of the user
	 * 
	 * @param user
	 * @return greeting
	 */
	@ApiOperation(value = "greeting", notes = "Generate greeting to user")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = Greeting.class, message = "Generate greeting successfully"),
			@ApiResponse(code = 400, message = "Validation error"),
			@ApiResponse(code = 406, message = "Accept Header must be json"),
			@ApiResponse(code = 500, message = "Server Error") })
	@PostMapping("/greeting")
	public Greeting greeting(@Valid @RequestBody UserRequestDTO user) {
		boolean isUserExits = false;
		if (user != null && (!user.getFirstName().isEmpty() || !user.getLastName().isEmpty())) {
			String firstName = user.getFirstName();
			String lastName = user.getLastName();
			log.debug("[firstName] : " + user.getFirstName() + " [lastName] :  " + user.getLastName());
			isUserExits = userVisitService.createUserVisit(user);
			return new Greeting(isUserExits, firstName, lastName);
		} else {
			return new Greeting();
		}

	}

}
