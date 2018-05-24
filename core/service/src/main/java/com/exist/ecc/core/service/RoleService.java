package com.exist.ecc.core.service;

import java.util.List;

import com.exist.ecc.core.model.dto.RoleDto;

public interface RoleService {
	public Integer addRole(RoleDto roleDto);
	public RoleDto addAndReturnRole(RoleDto roleDto);
	public RoleDto getRole(int id);
	public RoleDto getRoleByName(String roleName);
	public List<RoleDto> getAllRoles();
	public void updateRole(RoleDto role);
	public void deleteRole(int id);
	public boolean roleAlreadyExists(RoleDto roleDto);
}
