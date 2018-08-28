package com.cooksys.socialmediaassessment.exception;

public class UserAlreadyExistsException extends RuntimeException{

	private String username;

	public UserAlreadyExistsException(String username) {
		this.username = username;
	}

	public String getMessage() {
		return "Username '" + this.username + "' is already taken.";
	}
}
