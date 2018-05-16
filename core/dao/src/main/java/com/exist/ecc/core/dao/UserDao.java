package com.exist.ecc.core.dao;

import com.exist.ecc.core.model.Users;
import java.util.List;

public interface UserDao {
	public Users getUserByName(String userName);
	public Integer addUser(Users user);
	public Users getUser(int id);
	public List<Users> getAllUsers();
	public void deleteUser(int id);
	public void updateUser(Users user);
}
