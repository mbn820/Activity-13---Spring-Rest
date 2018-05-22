package com.exist.ecc.core.service.exceptions;

public class RoleAlreadyExistsException extends RuntimeException {
	private String roleName;

	public RoleAlreadyExistsException() {}
	public RoleAlreadyExistsException(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}

}
