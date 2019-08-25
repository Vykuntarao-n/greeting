package com.yk.greeting.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.yk.greeting.api.GreetingController;
import com.yk.greeting.config.Greeting;
import com.yk.greeting.model.UserRequestDTO;
import com.yk.greeting.service.UserService;
import com.yk.greeting.service.UserVisitService;

/**
 * 
 * @author Vykuntarao
 *
 */
@RestController
public class GreetingControllerImpl implements GreetingController {

	private static final Logger log = LoggerFactory.getLogger(GreetingControllerImpl.class);

	@Autowired
	UserService userService;

	@Autowired
	UserVisitService userVisitService;

	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	public Greeting greeting(UserRequestDTO user) {
		boolean isUserExits = false;
		if (user != null && (!user.getFirstName().isEmpty() || !user.getLastName().isEmpty())) {
			String firstName = user.getFirstName();
			String lastName = user.getLastName();
			log.debug("[firstName] : " + user.getFirstName() + " [lastName] :  " + user.getLastName());
			isUserExits = userVisitService.createUserVisit(firstName, lastName);
			return new Greeting(isUserExits, firstName, lastName);
		} else {
			return new Greeting();
		}

	}

}
