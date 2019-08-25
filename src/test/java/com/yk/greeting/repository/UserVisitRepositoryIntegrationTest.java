package com.yk.greeting.repository;



import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.yk.greeting.entity.User;
import com.yk.greeting.entity.UserVisit;
import com.yk.greeting.projection.VisitorProjection;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserVisitRepositoryIntegrationTest {
	
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	UserVisitRepository userVisitRepository ;
	
	@Test
	public void whenfindDistinctByFirstName_thenReturnUserFirstName() {
		User user = new User("FirstName", "LastName");
		UserVisit UserVisit = new UserVisit(); 
		UserVisit.setUser(user);
		entityManager.persist(UserVisit);
		entityManager.flush();
		Set<String> setFirstNames = userVisitRepository.findDistinctByFirstName();
		
		Set<String> value = new HashSet<String>() ;
		value.add(user.getFirstName()) ;
		assertThat(setFirstNames , is( equalTo(value)) ) ; 
	}
	
	
	@Test
	public void whenfindDistinctByLastName_thenReturnUserLastName() {
		User user = new User("FirstName", "LastName");
		UserVisit UserVisit = new UserVisit(); 
		UserVisit.setUser(user);
		entityManager.persist(UserVisit);
		entityManager.flush();
		Set<String> setFirstNames = userVisitRepository.findDistinctByLastName();
		
		Set<String> value = new HashSet<String>() ;
		value.add(user.getLastName()) ;
		assertThat(setFirstNames , is( equalTo(value)) ) ; 
	}
	
	
	@Test
	public void whenfindVisitor_henReturnVisitorProjectionList() {
		User user = new User("FirstName", "LastName");
		UserVisit UserVisit = new UserVisit(); 
		UserVisit.setUser(user);
		entityManager.persist(UserVisit);
		entityManager.flush();
		
		List<VisitorProjection> userProjectionList = userVisitRepository.findVisitors() ; 
		
		List<VisitorProjection>  visitorProjectionList = new ArrayList<VisitorProjection>(); 
		
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
		}) ; 
		
		assertThat( userProjectionList.get(0).getUserFirst() , is( visitorProjectionList.get(0).getUserFirst() ) ) ;
		assertThat( userProjectionList.get(0).getUserLast() , is( visitorProjectionList.get(0).getUserLast() ) ) ; 
		assertThat( userProjectionList.get(0).getVisitCount() , is( visitorProjectionList.get(0).getVisitCount()) ) ; 
	}

}
