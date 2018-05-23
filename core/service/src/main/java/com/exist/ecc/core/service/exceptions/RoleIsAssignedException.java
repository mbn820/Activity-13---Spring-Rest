package com.exist.ecc.core.service.exceptions;

public class RoleIsAssignedException extends RuntimeException {
	private int roleId;

	public RoleIsAssignedException() {}
	public RoleIsAssignedException(int roleId) {
		this.roleId = roleId;
	}

	public int getRoleId() {
		return roleId;
	}

}
