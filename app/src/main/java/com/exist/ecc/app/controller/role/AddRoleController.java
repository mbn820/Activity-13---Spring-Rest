package com.exist.ecc.app.controller.role;

import java.util.List;
import com.exist.ecc.core.service.RoleService;
import com.exist.ecc.core.model.dto.RoleDto;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class AddRoleController extends SimpleFormController {
	private RoleService roleService;

	public AddRoleController() {
		setCommandClass(RoleDto.class);
		setCommandName("role");
		setFormView("manageRoles");
		setSuccessView("manageRoles");
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@Override
	protected ModelAndView onSubmit(Object command) throws Exception {
		RoleDto roleToBeAdded = (RoleDto) command;
		String addRoleErrorMessage = "Role Already Exists!";

		ModelAndView result = new ModelAndView( getSuccessView() );

		try {
			roleService.addRole(roleToBeAdded);
		} catch( Exception e ) {
			result.addObject("addRoleErrorMessage", addRoleErrorMessage);
		}

		return result;
	}
}
