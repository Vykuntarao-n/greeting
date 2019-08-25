package com.yk.greeting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yk.greeting.entity.User;
import com.yk.greeting.exception.RecordNotFoundException;
import com.yk.greeting.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService ; 
	/**
	 * 
	 * @param id
	 * @return
	 * @throws RecordNotFoundException
	 */
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Long id) throws RecordNotFoundException {
		User entity = userService.getUserById(id);
		return new ResponseEntity<User>(entity, new HttpHeaders(), HttpStatus.OK);
	}

}
