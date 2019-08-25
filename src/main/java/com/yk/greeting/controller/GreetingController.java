package com.yk.greeting.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yk.greeting.model.Greeting;
import com.yk.greeting.model.UserRequestDTO;
import com.yk.greeting.service.UserVisitAuditService;
import com.yk.greeting.util.GreetingFunction;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author Vykuntarao
 *
 */
@RestController
public class GreetingController {

	private static final Logger log = LoggerFactory.getLogger(GreetingController.class);

	@Autowired
	UserVisitAuditService userVisitService;

	/**
	 * This will greet the user with the Welcome message with the name of the user
	 * 
	 * @param user
	 * @return greeting
	 */
	@ApiOperation(value = "greeting", notes = "Generate greeting to user")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = String.class, message = "Generate greeting successfully"),
			@ApiResponse(code = 400, message = "Validation error"),
			@ApiResponse(code = 406, message = "Accept Header must be json"),
			@ApiResponse(code = 500, message = "Server Error") })
	@PostMapping("/greeting")
	public String greeting(@Valid @RequestBody UserRequestDTO user) {
		return Optional.ofNullable(user).filter(userFilter -> ! (  StringUtils.isEmpty(userFilter.getFirstName())
				&& StringUtils.isEmpty(userFilter.getLastName()))).map(userMap -> {
					log.debug("In side if ... ");
					boolean isUserExits = false;
					isUserExits = userVisitService.manageUserVisit(userMap);
					return new GreetingFunction().apply(userMap, isUserExits);
				}).orElse("Welcome !");

	}

}
