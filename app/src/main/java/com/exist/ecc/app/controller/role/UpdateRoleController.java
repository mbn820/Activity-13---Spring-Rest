package com.exist.ecc.app.controller.role;

import java.util.List;
import com.exist.ecc.core.service.RoleService;
import com.exist.ecc.core.model.dto.RoleDto;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class UpdateRoleController extends AbstractController {
	private RoleService roleService;

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idToBeUpdated = Integer.parseInt( request.getParameter("idToBeUpdated") );
		String newRoleName = request.getParameter( "newRoleName" );

		RoleDto updatedRole = roleService.getRole( idToBeUpdated );
		updatedRole.setRoleName(newRoleName);

		try {
			roleService.updateRole(updatedRole);
		} catch( Exception e ) {
			response.sendError(response.SC_BAD_REQUEST);
		}

		return null;
	}
}
