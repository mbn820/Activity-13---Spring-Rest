package com.exist.ecc.app.restcontroller.errors;

public class Error {
	private String message;

	public Error(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
