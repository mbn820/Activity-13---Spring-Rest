package com.exist.ecc.core.service.impl;

import java.util.List;
import com.exist.ecc.core.dao.RoleDao;
import com.exist.ecc.core.model.Role;
import com.exist.ecc.core.model.dto.RoleDto;
import com.exist.ecc.core.service.RoleService;
import com.exist.ecc.core.service.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.access.prepost.PreAuthorize;
import com.exist.ecc.core.service.exceptions.RoleAlreadyExistsException;
import com.exist.ecc.core.service.exceptions.RoleNotFoundException;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	private RoleDao roleDao;
	private DtoMapper dtoMapper;

	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Autowired
	public void setDtoMapper(DtoMapper dtoMapper) {
		this.dtoMapper = dtoMapper;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Integer addRole(RoleDto roleDto) {
		if ( roleAlreadyExists(roleDto) ) {
			throw new RoleAlreadyExistsException();
		} else {
			Role roleToBeAdded = dtoMapper.mapToRole(roleDto);
			return roleDao.addRole(roleToBeAdded);
		}
	}

	@Transactional(readOnly = true)
	public RoleDto getRole(int id) {
		Role role = roleDao.getRole(id);
		if (role == null) { throw new RoleNotFoundException(); }
		return dtoMapper.mapToRoleDto(role);
	}

	@Transactional(readOnly = true)
	public RoleDto getRoleByName(String roleName) {
		Role role = roleDao.getRoleByName(roleName);
		return dtoMapper.mapToRoleDto(role);
	}

	@Transactional(readOnly = true)
	public List<RoleDto> getAllRoles() {
		List<Role> roles = roleDao.getAllRoles();
		return dtoMapper.mapToRoleDtoList(roles);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void updateRole(RoleDto role) {
		if ( roleAlreadyExists(role) ) {
			throw new RoleAlreadyExistsException(); // create RoleAlreadyExistsException
		} else {
			Role roleToBeUpdated = dtoMapper.mapToRole(role);
			roleDao.updateRole(roleToBeUpdated);
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void updateRoleName(int roleId, String newRoleName) {
		if ( roleAlreadyExists(newRoleName) ) {
			return;
		}
		Role roleToBeUpdated = roleDao.getRole(roleId);
		roleToBeUpdated.setRoleName(newRoleName);
		roleDao.updateRole(roleToBeUpdated);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void deleteRole(int id) {
		if( !getRole(id).getPersons().isEmpty() ) {
			throw new RuntimeException();
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
