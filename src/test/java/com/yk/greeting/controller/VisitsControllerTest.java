package com.yk.greeting.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.yk.greeting.model.VisitSummary;
import com.yk.greeting.projection.VisitorProjection;
import com.yk.greeting.service.UserVisitAuditService;

@RunWith(MockitoJUnitRunner.class)
public class VisitsControllerTest {

	@Mock
	UserVisitAuditService userVisitService;
	@InjectMocks
	VisitsController a = new VisitsController();

	@Test
	public void test() {

		VisitSummary visitSummary = new VisitSummary();
		List<VisitorProjection> visitorProjectionList = new ArrayList<VisitorProjection>();
		visitorProjectionList.add(new VisitorProjection() {

			@Override
			public Long getVisitCount() {
				// TODO Auto-generated method stub
				return new Long(1);
			}

			@Override
			public String getUserLast() {
				// TODO Auto-generated method stub
				return "LastName";
			}

			@Override
			public String getUserFirst() {
				// TODO Auto-generated method stub
				return "FirstName";
			}
		});

		when(userVisitService.getVisits()).thenReturn(visitSummary);
		assertEquals(visitSummary.getVisitors(), (a.getVisits().getVisitors()));

	}

}
