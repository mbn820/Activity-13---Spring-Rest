package com.exist.ecc.core.service.exceptions;

public class UserNameAlreadyTakenException extends RuntimeException {
	private String userName;

	public UserNameAlreadyTakenException() {}
	public UserNameAlreadyTakenException(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

}
