package com.exist.ecc.core.model.dto;

public class UsersDto {

	private int id;
	private String userName;
	private String password;
	private String userRole;

	public UsersDto() {}
	public UsersDto(String userName, String password, String userRole) {
		this.userName = userName;
		this.password = password;
		this.userRole = userRole;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUsersName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsersRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUsersName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getUsersRole() {
		return userRole;
	}
}
