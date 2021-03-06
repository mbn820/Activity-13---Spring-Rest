package com.exist.ecc.core.service.exceptions;

public class RoleNotFoundException extends RuntimeException {
	private int roleId;

	public RoleNotFoundException() {}
	public RoleNotFoundException(int roleId) {
		this.roleId = roleId;
	}

	public int getRoleId() {
		return roleId;
	}

}
