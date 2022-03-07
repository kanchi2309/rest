package com.spring.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception{
	
	public UserNotFoundException(int id) {
		super(String.format("User not found with Id = %s", id));
		
	}
	
}
