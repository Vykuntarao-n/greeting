package com.yk.greeting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yk.greeting.model.VisitSummary;
import com.yk.greeting.service.UserVisitAuditService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class VisitsController  {
	@Autowired
	UserVisitAuditService userVisitService;
	
	/**
	 * Returns all the statistics of the user Visits
	 * @return Visits statistics
	 */
	@ApiOperation(value = "visits", notes = "Returns all the statistics of the user Visits")
	@ApiResponses(value = { @ApiResponse(code = 200, response = VisitSummary.class  ,message = "Success"),
			@ApiResponse(code = 400, message = "Validation error"),
			@ApiResponse(code = 406, message = "Accept Header must be json"),
			@ApiResponse(code = 500, message = "Server Error") })
	@GetMapping("/visits")
	public VisitSummary getVisits() {
		return userVisitService.getVisits();
	}
}
