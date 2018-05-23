package com.exist.ecc.core.service.exceptions;

public class UserNotFoundException extends RuntimeException {
	private int userId;

	public UserNotFoundException() {}
	public UserNotFoundException(int userId) {
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

}
