package com.exist.ecc.core.service.exceptions;

public class PersonNotFoundException extends RuntimeException {
	private int personId;

	public PersonNotFoundException() {}
	public PersonNotFoundException(int personId) {
		this.personId = personId;
	}

	public int getPersonId() {
		return personId;
	}

}
