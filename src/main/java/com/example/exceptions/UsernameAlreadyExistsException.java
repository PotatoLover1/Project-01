package com.example.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException{
	static final long serialVersionUID = 1L;
	public UsernameAlreadyExistsException() {
		super("A username was created that already exists in the database");
	}

}
