package com.exist.ecc.core.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UsersDto {

	private int id;

	private String userName;

	@JsonIgnore
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

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public int getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getUserRole() {
		return userRole;
	}
}
