package com.cooksys.custom.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class IdNotAvailableException extends Exception {
	private Long id;

	public IdNotAvailableException(Long id) {
		this.id = id;
	}
	
	@Override
	public String getMessage() {
		return id + " is already in use!";
	}
}
