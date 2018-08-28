package com.cooksys.socialmediaassessment.exception;

public class InvalidCredentialsException extends RuntimeException{

	public InvalidCredentialsException() {}

	public String getMessage() {
		return "Credentials provided are missing a username a password, or both.";
	}
}
