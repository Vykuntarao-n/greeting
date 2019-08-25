package com.yk.greeting.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yk.greeting.config.Greeting;
import com.yk.greeting.model.UserRequestDTO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author Vykuntarao
 *
 */
@RestController
public interface GreetingController {
	/**
	 * /**
	 * This will greet the user with the Welcome message with the name of the user
	 * @param user
	 * @return greeting
	 */
	@ApiOperation(value = "greeting", notes = "Generate greeting to user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Generate greeting successfully"),
			@ApiResponse(code = 400, message = "Validation error"),
			@ApiResponse(code = 406, message = "Accept Header must be json"),
			@ApiResponse(code = 500, message = "Server Error") })
	@PostMapping("/greeting")
	public Greeting greeting(@RequestBody UserRequestDTO user);

}
