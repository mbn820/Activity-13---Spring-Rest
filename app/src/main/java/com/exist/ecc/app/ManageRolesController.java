package com.exist.ecc.app;

import java.util.List;
import com.exist.ecc.core.service.RoleService;
import com.exist.ecc.core.model.dto.RoleDto;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class ManageRolesController extends AbstractController {
	private RoleService roleService;

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<RoleDto> existingRoles = roleService.getAllRoles();

		ModelAndView model = new ModelAndView("ManageRoles");
		model.addObject( "role", new RoleDto() );
		model.addObject("existingRoles", existingRoles);

		return model;
	}
}
