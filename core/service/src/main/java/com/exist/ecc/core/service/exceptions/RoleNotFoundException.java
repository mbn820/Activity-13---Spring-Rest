package com.exist.ecc.core.service.exceptions;

public class RoleNotFoundException extends RuntimeException {
	private String roleName;

	public RoleNotFoundException() {}
	public RoleNotFoundException(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}

}
