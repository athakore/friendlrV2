package com.cooksys.custom.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends Exception {
	private Long id;
	public PersonNotFoundException(Long id) {
		this.id = id;
	}
	
	@Override
	public String getMessage() {
		return "Could not find a person with the ID " + id + "!";
	}
}
