package com.yk.greeting.model;

import javax.validation.constraints.Size;

public class UserRequestDTO {

	@Size(max = 200, message = "FirstName Me must be less than 200 characters")
	String firstName ;

	@Size(max = 200, message = "LastName Me must be less than 200 characters")
	String lastName ;

	/**
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public UserRequestDTO(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
