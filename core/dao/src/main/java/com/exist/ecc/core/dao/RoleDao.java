package com.exist.ecc.core.dao;

import com.exist.ecc.core.model.Role;
import java.util.List;

public interface RoleDao {
	public Integer addRole(Role role);
	public Role getRole(int id);
	public List<Role> getAllRoles();
	public void deleteRole(int id);
	public void updateRole(Role role);
}
