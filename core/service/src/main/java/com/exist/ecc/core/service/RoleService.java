package com.exist.ecc.core.service;

import com.exist.ecc.core.model.dto.RoleDto;
import java.util.List;

public interface RoleService {
	public Integer addRole(RoleDto roleDto) throws Exception;
	public RoleDto getRole(int id);
	public List<RoleDto> getAllRoles();
	public void updateRole(RoleDto role) throws Exception;
	public void deleteRole(int id) throws Exception;
}
