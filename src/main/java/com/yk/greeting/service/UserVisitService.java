package com.yk.greeting.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yk.greeting.entity.User;
import com.yk.greeting.entity.UserVisit;
import com.yk.greeting.entity.Visits;
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
	public boolean createUserVisit(String firstName, String lastName) {
		Optional<User> user = userRepository.findByFirstNameAndLastNameAllIgnoreCase(firstName, lastName);
		if (user.isPresent()) {
			log.debug("User - " + user.get());
			UserVisit userVisit = new UserVisit();
			userVisit.setUser(user.get());
			userVisitRepository.save(userVisit);
			return true;
		} else {
			UserVisit userVisit = new UserVisit();
			userVisit.setUser(new User(firstName, lastName));
			userVisitRepository.save(userVisit);
			return false;
		}

	}

	/**
	 * Returns visits Statistics     
	 * @return Visits
	 */
	public Visits getVisits() {
		Visits visits = new Visits();
		visits.setTotalVisits(userVisitRepository.count());
		visits.setVisitorFirstNames(userVisitRepository.findDistinctByFirstName());
		visits.setVisitorLastNames(userVisitRepository.findDistinctByLastName());
		visits.setVisitors(userVisitRepository.findVisitors());
		return visits;
	}

}
