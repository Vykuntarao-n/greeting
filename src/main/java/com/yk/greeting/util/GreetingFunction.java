package com.yk.greeting.util;

import java.util.Optional;
import java.util.function.BiFunction;

import com.yk.greeting.model.UserRequestDTO;

public class GreetingFunction implements BiFunction<UserRequestDTO, Boolean, String>{
	private static final String TEMPLATE_BACK = "Welcome back %s, %s !";
	private static final String TEMPLATE = "Welcome %s, %s !";
	@Override
	public String apply(UserRequestDTO t, Boolean u) {
		if (u) {
			return String.format(TEMPLATE_BACK, printName(t.getFirstName())  , printName(t.getLastName()));
		}else {
			return String.format(TEMPLATE, printName(t.getFirstName())  , printName(t.getLastName()));
		}
		
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	private String printName(String value){
		return Optional.ofNullable(value).map(val -> val).orElse(""); 
	}
	
	
}