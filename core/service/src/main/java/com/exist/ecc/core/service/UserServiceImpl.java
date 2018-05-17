package com.exist.ecc.core.service;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.exist.ecc.core.model.Users;
import com.exist.ecc.core.model.dto.UsersDto;
import com.exist.ecc.core.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private DtoMapper dtoMapper;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setDtoMapper(DtoMapper dtoMapper) {
		this.dtoMapper = dtoMapper;
	}

	@Transactional(readOnly = true)
	public UsersDto getUserByName(String userName) {
		Users user = userDao.getUserByName(userName);
		return dtoMapper.mapToUserDto(user);
	}

	public Integer addUser(UsersDto userDto) {
		Users userToBeAdded = dtoMapper.mapToUser(userDto);
		return userDao.addUser(userToBeAdded);
	}

	@Transactional(readOnly = true)
	public UsersDto getUser(int id) {
		Users user = userDao.getUser(id);
		return dtoMapper.mapToUserDto(user);
	}

	@Transactional(readOnly = true)
	public List<UsersDto> getAllUsers() {
		List<Users> allUsers = userDao.getAllUsers();
		return dtoMapper.mapToUserDtoList(allUsers);
	}

	public void deleteUser(int id) {
		userDao.deleteUser(id);
	}

	public void updateUser(UsersDto userDto) {
		Users userToBeUpdated = dtoMapper.mapToUser(userDto);
		userDao.updateUser(userToBeUpdated);
	}

	public boolean userNameAlreadyExists(String userName) {
		for( UsersDto user : getAllUsers() ) {
			if( user.getUserName().equals(userName) ) {
				return true;
			}
		}

		return false;
	}
}
