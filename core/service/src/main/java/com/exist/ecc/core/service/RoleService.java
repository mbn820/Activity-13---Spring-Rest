package com.exist.ecc.core.service;

import java.util.List;
import com.exist.ecc.core.model.dto.RoleDto;

public interface RoleService {
	public Integer addRole(RoleDto roleDto) throws Exception;
	public RoleDto getRole(int id);
	public List<RoleDto> getAllRoles();
	public void updateRole(RoleDto role) throws Exception;
	public void deleteRole(int id) throws Exception;
	public boolean roleAlreadyExists(RoleDto roleDto);
}
