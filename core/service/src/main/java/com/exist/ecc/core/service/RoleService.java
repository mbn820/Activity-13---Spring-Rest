package com.exist.ecc.core.service;

import com.exist.ecc.core.dao.RoleDao;
import com.exist.ecc.core.model.Role;
import java.util.List;

public class RoleService {
	public Integer addRole(Role role) throws Exception {
		if ( roleAlreadyExists(role) ) {
			throw new Exception();
		} else {
			return new RoleDao().addRole(role);
		}
	}

	public Role getRole(int id) {
		return new RoleDao().getRole(id);
	}

	public List<Role> getAllRoles() {
		return new RoleDao().getAllRoles();
	}

	public void updateRole(Role role) {
		new RoleDao().updateRole(role);
	}

	public void deleteRole(int id) throws Exception {
		if( roleIsAssignedToAPerson(id) ) {
			throw new Exception();
		} else {
			new RoleDao().deleteRole(id);
		}
	}

	public boolean roleAlreadyExists(Role role) {
		return getAllRoles().contains(role);
	}

	public boolean roleIsAssignedToAPerson(int id) {
		return !( getRole(id).getPersons().isEmpty() );
	}
}
