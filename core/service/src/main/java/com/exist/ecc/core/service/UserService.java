package com.exist.ecc.core.service;

import java.util.List;

import com.exist.ecc.core.model.dto.UsersDto;

public interface UserService {
	public UsersDto getUserByName(String userName);
	public Integer addUser(UsersDto user);
	public UsersDto addAndReturnUser(UsersDto user);
	public UsersDto getUser(int id);
	public List<UsersDto> getAllUsers();
	public void deleteUser(int id);
	public void updateUser(UsersDto user);
	public boolean userNameAlreadyExists(String userName);
}
