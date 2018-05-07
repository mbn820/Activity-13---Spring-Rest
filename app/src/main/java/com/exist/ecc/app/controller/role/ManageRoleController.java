package com.exist.ecc.app.controller.role;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.exist.ecc.core.service.RoleService;
import com.exist.ecc.core.model.dto.RoleDto;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class ManageRoleController extends SimpleFormController {
	private RoleService roleService;

	public ManageRoleController() {
		setCommandClass(RoleDto.class);
		setCommandName("role");
		setFormView("ManageRoles");
		setSuccessView("redirect:/manageRoles.htm");
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@Override
	protected Map referenceData(HttpServletRequest request) {
		Map reference = new HashMap();
		reference.put( "existingRoles", roleService.getAllRoles() );
		return reference;
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request) {
		return new RoleDto();
	}

	@Override
	protected ModelAndView onSubmit(Object command, BindException errors) throws Exception {
		RoleDto roleToBeAdded = (RoleDto) command;

		roleService.addRole(roleToBeAdded);

		return new ModelAndView( getSuccessView() );
	}
}
