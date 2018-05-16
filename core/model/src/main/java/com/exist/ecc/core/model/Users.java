package com.exist.ecc.core.model;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class Users {

	@Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    @SequenceGenerator (name = "user_id_generator", sequenceName = "user_seq", allocationSize = 1)
	private int id;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "user_role")
	private String userRole;

	public Users() {}
	public Users(String userName, String password, String userRole) {
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
