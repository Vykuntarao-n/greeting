package com.yk.greeting.model;

import com.yk.greeting.entity.User;

/**
 * @author Vykuntarao
 *
 */
public class Greeting {

	private final String content;
	private static final String TEMPLATE = "Welcome, %s %s %s !";
	/*
	 * Default Constructor
	 */
	public Greeting() {
		this.content = String.format(TEMPLATE , "" , "" , "" ) ; 
	}
	
	/**
	 * Constructor with the User as parameter
	 * @param user
	 */
	public Greeting(User user) {
		this.content = String.format(TEMPLATE, user.getFirstName(), user.getLastName());
	}

	/**
	 * 
	 * @param isUserExits
	 * @param firstName
	 * @param lastName
	 */
	public Greeting(boolean isUserExits, String firstName, String lastName) {
		if (isUserExits) {
			this.content = String.format(TEMPLATE, "back", firstName, lastName);
		} else {
			this.content = String.format(TEMPLATE, "", firstName, lastName);
		}
	}

	/**
	 * Geeting
	 * @return
	 */
	public String getContent() {
		return content;
	}
}