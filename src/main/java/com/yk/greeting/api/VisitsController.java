package com.yk.greeting.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yk.greeting.entity.Visits;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/visits")
public interface VisitsController {
	/**
	 * Returns all the statistics of the user Visits
	 * @return Visits statistics
	 */
	@ApiOperation(value = "visits", notes = "Returns all the statistics of the user Visits")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucess"),
			@ApiResponse(code = 400, message = "Validation error"),
			@ApiResponse(code = 406, message = "Accept Header must be json"),
			@ApiResponse(code = 500, message = "Server Error") })
	@GetMapping
	public Visits getVisits();

}
