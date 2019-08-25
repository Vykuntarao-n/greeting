package com.yk.greeting.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.yk.greeting.entity.User;
import com.yk.greeting.exception.RecordNotFoundException;
import com.yk.greeting.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return
	 */
	public List<User> getAllUsers(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		Page<User> pagedResult = userRepository.findAll(paging);

		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<User>();
		}
	}
	/**
	 * 
	 * @param id
	 * @return
	 * @throws RecordNotFoundException
	 */
	public User getUserById(Long id) throws RecordNotFoundException {
		Optional<User> User = userRepository.findById(id);
		if (User.isPresent()) {
			return User.get();
		} else {
			throw new RecordNotFoundException("No User record exist for given id");
		}
	}
	/**
	 * 
	 * @param user
	 * @return
	 * @throws RecordNotFoundException
	 */
	public User createOrUpdateUser(User user) throws RecordNotFoundException {
		Optional<User> User = userRepository.findById(user.getId());

		if (User.isPresent()) {
			User newEntity = User.get();
			newEntity.setFirstName(user.getFirstName());
			newEntity.setLastName(user.getLastName());

			newEntity = userRepository.save(newEntity);

			return newEntity;
		} else {
			user = userRepository.save(user);

			return user;
		}
	}
	/**
	 * 
	 * @param id
	 * @throws RecordNotFoundException
	 */
	public void deleteUserById(Long id) throws RecordNotFoundException {
		Optional<User> User = userRepository.findById(id);

		if (User.isPresent()) {
			userRepository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No User record exist for given id");
		}
	}
		
}
