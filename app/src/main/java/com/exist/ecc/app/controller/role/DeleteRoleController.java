package com.exist.ecc.app.controller.role;

import java.util.List;
import com.exist.ecc.core.service.RoleService;
import com.exist.ecc.core.model.dto.RoleDto;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class DeleteRoleController extends AbstractController {
	private RoleService roleService;

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idOfRoleToBeDeleted = Integer.parseInt( request.getParameter("idToBeDeleted") );

		roleService.deleteRole( idOfRoleToBeDeleted );

		return null;
	}
}
