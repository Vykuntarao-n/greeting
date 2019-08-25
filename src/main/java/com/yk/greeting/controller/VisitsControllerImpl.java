package com.yk.greeting.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.yk.greeting.api.VisitsController;
import com.yk.greeting.entity.Visits;
import com.yk.greeting.service.UserVisitService;

public class VisitsControllerImpl implements VisitsController {
	@Autowired
	UserVisitService userVisitService;
	
	@Override
	public Visits getVisits() {
		return userVisitService.getVisits();
	}
}
