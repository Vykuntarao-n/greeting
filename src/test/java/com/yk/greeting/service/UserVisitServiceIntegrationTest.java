package com.yk.greeting.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.yk.greeting.entity.User;
import com.yk.greeting.entity.Visits;
import com.yk.greeting.projection.VisitorProjection;
import com.yk.greeting.repository.UserRepository;
import com.yk.greeting.repository.UserVisitRepository;

@RunWith(SpringRunner.class)
public class UserVisitServiceIntegrationTest {

	@TestConfiguration
	static class UserVisitServiceTestContextConfiguration {

		@Bean
		public UserVisitService userVisitServiceService() {
			return new UserVisitService();
		}
	}

	@Autowired
	UserVisitService userVisitService;

	@MockBean
	private UserVisitRepository userVisitRepository;

	@MockBean
	UserRepository userRepository;
	/**
	 * 
	 * @return
	 */
	private Visits getVisits() {
		User user = new User("FirstName", "LastName");
		Visits visits = new Visits();
		visits.setTotalVisits(1);
		Set<String> fisrtName = new HashSet<String>();
		fisrtName.add(user.getFirstName());
		visits.setVisitorFirstNames(fisrtName);
		Set<String> lastName = new HashSet<String>();
		lastName.add(user.getLastName());
		visits.setVisitorFirstNames(lastName);

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
				return user.getLastName();
			}

			@Override
			public String getUserFirst() {
				// TODO Auto-generated method stub
				return user.getFirstName();
			}
		});
		visits.setVisitors(visitorProjectionList);
		
		return visits;

	}
	
	/**
	 * 
	 */
	@Before
	public void setUp() {
		Visits visits = getVisits();
		Mockito.when(userVisitRepository.count()).thenReturn(visits.getTotalVisits());
		Mockito.when(userVisitRepository.findDistinctByFirstName()).thenReturn(visits.getVisitorFirstNames());
		Mockito.when(userVisitRepository.findDistinctByLastName()).thenReturn(visits.getVisitorLastNames());
		Mockito.when(userVisitRepository.findVisitors()).thenReturn(visits.getVisitors());

	}
	/**
	 * 
	 */
	@Test
	public void whenfindByFirstNameAndLastNameAllIgnoreCase_thenReturnOptionalUser() {
		Visits visitsService = userVisitService.getVisits();
		assertThat(visitsService.getTotalVisits(), is(getVisits().getTotalVisits()));
		assertThat(visitsService.getVisitorFirstNames(), is(getVisits().getVisitorFirstNames()));
		assertThat(visitsService.getVisitorLastNames(), is(getVisits().getVisitorLastNames()));
		assertThat(visitsService.getVisitors().get(0).getUserFirst() , is( getVisits().getVisitors().get(0).getUserFirst() ) ) ;
		assertThat(visitsService.getVisitors().get(0).getUserLast() , is( getVisits().getVisitors().get(0).getUserLast() ) ) ; 
		assertThat(visitsService.getVisitors().get(0).getVisitCount() , is( getVisits().getVisitors().get(0).getVisitCount()) ) ; 
	}
}
