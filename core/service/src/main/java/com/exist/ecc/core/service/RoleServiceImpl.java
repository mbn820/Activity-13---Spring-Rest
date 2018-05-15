package com.exist.ecc.core.service;

import java.util.List;
import com.exist.ecc.core.dao.RoleDao;
import com.exist.ecc.core.model.Role;
import com.exist.ecc.core.model.dto.RoleDto;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private DtoMapper dtoMapper;

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public void setDtoMapper(DtoMapper dtoMapper) {
		this.dtoMapper = dtoMapper;
	}

	public Integer addRole(RoleDto roleDto) throws Exception {
		if ( roleAlreadyExists(roleDto) ) {
			throw new Exception();
		} else {
			Role roleToBeAdded = dtoMapper.mapToRole(roleDto);
			return roleDao.addRole(roleToBeAdded);
		}
	}

	public RoleDto getRole(int id) {
		Role role = roleDao.getRole(id);
		return dtoMapper.mapToRoleDto(role);
	}

	public RoleDto getRoleByName(String roleName) {
		Role role = roleDao.getRoleByName(roleName);
		return dtoMapper.mapToRoleDto(role);
	}

	public List<RoleDto> getAllRoles() {
		List<Role> roles = roleDao.getAllRoles();
		return dtoMapper.mapToRoleDtoList(roles);
	}

	public void updateRole(RoleDto role) throws Exception {
		if ( roleAlreadyExists(role) ) {
			throw new Exception(); // create RoleAlreadyExistsException
		} else {
			Role roleToBeUpdated = dtoMapper.mapToRole(role);
			roleDao.updateRole(roleToBeUpdated);
		}
	}

	public void updateRoleName(int roleId, String newRoleName) {
		if ( roleAlreadyExists(newRoleName) ) {
			return;
		}
		Role roleToBeUpdated = roleDao.getRole(roleId);
		roleToBeUpdated.setRoleName(newRoleName);
		roleDao.updateRole(roleToBeUpdated);
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

	public boolean roleAlreadyExists(String roleName) {
		for ( Role role : roleDao.getAllRoles() ) {
			if ( role.getRoleName().equals(roleName) ) {
				return true;
			}
		}
		return false;
	}

}
