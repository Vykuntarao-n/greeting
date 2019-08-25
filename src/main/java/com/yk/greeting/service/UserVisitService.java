package com.yk.greeting.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yk.greeting.entity.User;
import com.yk.greeting.entity.UserVisit;
import com.yk.greeting.model.UserRequestDTO;
import com.yk.greeting.model.VisitSummary;
import com.yk.greeting.repository.UserRepository;
import com.yk.greeting.repository.UserVisitRepository;

@Service
public class UserVisitService {

	private static final Logger log = LoggerFactory.getLogger(UserVisitService.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserVisitRepository userVisitRepository;

	/**
	 * This Method will return true of a new user is created in user table otherwise
	 * return false
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	public boolean createUserVisit(UserRequestDTO userReq) {
		boolean isUserCreated = false ; 
		try {
			Optional<User> user = userRepository.findByFirstNameAndLastNameAllIgnoreCase(userReq.getFirstName(), userReq.getLastName());
			UserVisit userVisit = new UserVisit();
			if (user.isPresent()) {
				log.debug("User - " + user.get());
				userVisit.setUser(user.get());
				isUserCreated =  true;
			} else {
				userVisit.setUser(new User(userReq.getFirstName(), userReq.getLastName()));
				isUserCreated =  false;
			}
			userVisitRepository.save(userVisit);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return isUserCreated ; 
	}

	/**
	 * Returns visits Statistics     
	 * @return Visits
	 */
	public VisitSummary getVisits() {
		VisitSummary visits = new VisitSummary();
		visits.setTotalVisits(userVisitRepository.count());
		visits.setVisitorFirstNames(userVisitRepository.findDistinctByFirstName());
		visits.setVisitorLastNames(userVisitRepository.findDistinctByLastName());
		visits.setVisitors(userVisitRepository.findVisitors());
		return visits;
	}

}
