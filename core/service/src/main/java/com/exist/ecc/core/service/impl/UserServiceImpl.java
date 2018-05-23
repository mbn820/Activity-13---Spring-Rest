package com.exist.ecc.core.service.impl;

import java.util.List;
import com.exist.ecc.core.model.Users;
import com.exist.ecc.core.model.dto.UsersDto;
import com.exist.ecc.core.dao.UserDao;
import com.exist.ecc.core.service.UserService;
import com.exist.ecc.core.service.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.access.prepost.PreAuthorize;
import com.exist.ecc.core.service.exceptions.UserNotFoundException;
import com.exist.ecc.core.service.exceptions.UserNameAlreadyTakenException;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	private UserDao userDao;
	private DtoMapper dtoMapper;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setDtoMapper(DtoMapper dtoMapper) {
		this.dtoMapper = dtoMapper;
	}

	@Transactional(readOnly = true)
	public UsersDto getUserByName(String userName) {
		Users user = userDao.getUserByName(userName);
		if (user == null) {
			throw new UserNotFoundException();
		}
		return dtoMapper.mapToUserDto(user);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Integer addUser(UsersDto userDto) {
		String userName = userDto.getUserName();
		if ( userNameAlreadyExists(userName) ) {
			throw new UserNameAlreadyTakenException(userName);
		}
		Users userToBeAdded = dtoMapper.mapToUser(userDto);
		return userDao.addUser(userToBeAdded);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public UsersDto addAndReturnUser(UsersDto userDto) {
		return getUser( addUser(userDto) );
	}

	@Transactional(readOnly = true)
	public UsersDto getUser(int id) {
		Users user = userDao.getUser(id);
		if (user == null) {
			throw new UserNotFoundException(id);
		}
		return dtoMapper.mapToUserDto(user);
	}

	@Transactional(readOnly = true)
	public List<UsersDto> getAllUsers() {
		List<Users> allUsers = userDao.getAllUsers();
		return dtoMapper.mapToUserDtoList(allUsers);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void deleteUser(int id) {
		getUser(id); // trigger notFoundException
		userDao.deleteUser(id);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void updateUser(UsersDto userDto) {
		UsersDto origUser = getUser( userDto.getId() );
		String origName = origUser.getUserName();
		String newName = userDto.getUserName();

		if ( !origName.equals(newName) && userNameAlreadyExists(newName) ) {
			throw new UserNameAlreadyTakenException(newName);
		}

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
