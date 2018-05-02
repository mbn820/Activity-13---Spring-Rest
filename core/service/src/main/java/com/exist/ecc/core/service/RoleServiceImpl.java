package com.exist.ecc.core.service;

import com.exist.ecc.core.dao.RoleDao;
import com.exist.ecc.core.dao.RoleDaoImpl;
import com.exist.ecc.core.model.Role;
import com.exist.ecc.core.model.dto.RoleDto;
import java.util.List;

public class RoleServiceImpl implements RoleService {
	private RoleDao roleDao;
	private MapperUtil mapperUtil;

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public void setMapperUtil(MapperUtil mapperUtil) {
		this.mapperUtil = mapperUtil;
	}

	public Integer addRole(RoleDto roleDto) throws Exception {
		if ( roleAlreadyExists(roleDto) ) {
			throw new Exception();
		} else {
			Role roleToBeAdded = mapperUtil.mapToRole(roleDto);
			return roleDao.addRole(roleToBeAdded);
		}
	}

	public RoleDto getRole(int id) {
		Role role = roleDao.getRole(id);
		return mapperUtil.mapToRoleDto(role);
	}

	public List<RoleDto> getAllRoles() {
		List<Role> roles = roleDao.getAllRoles();
		return mapperUtil.mapToRoleDtoList(roles);
	}

	public void updateRole(RoleDto role) throws Exception {
		if ( roleAlreadyExists(role) ) {
			throw new Exception(); // create RoleAlreadyExistsException
		} else {
			Role roleToBeUpdated = mapperUtil.mapToRole(role);
			roleDao.updateRole(roleToBeUpdated);
		}
	}

	public void deleteRole(int id) throws Exception {
		if( !getRole(id).getPersons().isEmpty() ) {
			throw new Exception();
		} else {
			roleDao.deleteRole(id);
		}
	}

	public boolean roleAlreadyExists(RoleDto role) {
		return getAllRoles().contains(role);
	}
}
