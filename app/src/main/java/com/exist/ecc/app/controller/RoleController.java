package com.exist.ecc.app.controller;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletResponse;
import com.exist.ecc.app.validator.RoleValidator;
import com.exist.ecc.core.model.dto.PersonDto;
import com.exist.ecc.core.model.dto.RoleDto;
import com.exist.ecc.core.model.dto.ContactDto;
import com.exist.ecc.core.service.PersonService;
import com.exist.ecc.core.service.RoleService;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.BindingResult;
import org.springframework.http.ResponseEntity;

@Controller
public class RoleController {
    private PersonService personService;
    private RoleService roleService;
	private RoleValidator roleValidator;
    public static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
	public void setRoleValidator(RoleValidator roleValidator) {
		this.roleValidator = roleValidator;
	}

	@ModelAttribute("existingRoles")
	public List<RoleDto> populateRoles() {
		return roleService.getAllRoles();
	}


	@RequestMapping(value = "/role/manageRoles", method = RequestMethod.GET)
	public String loadManageRolesPage(ModelMap modelMap) {
        LOGGER.debug("Loading Manage Roles Form...");
		modelMap.addAttribute( "role", new RoleDto() );
		return "role/ManageRoles";
	}

	@RequestMapping(value = "/role/addRole", method = RequestMethod.POST)
	public String processAddRoleFormSubmit(@ModelAttribute("role") @Validated RoleDto roleToBeAdded,
									       BindingResult result) {

        LOGGER.debug("Processing role form submit...");
		roleValidator.validate(roleToBeAdded, result);
		if ( result.hasErrors() ) {
			return "role/ManageRoles";
		}

		try {
			roleService.addRole(roleToBeAdded);
		} catch(Exception e) {

		}

		return "redirect:/role/manageRoles";
	}

	@RequestMapping(value = "/role/updateRole", method = RequestMethod.POST)
	public void processUpdateRoleFormSubmit(@RequestParam(value = "idToBeUpdated") Integer idToBeUpdated,
						   @RequestParam(value = "newRoleName") String newRoleName,
						   HttpServletResponse response) throws Exception {

        LOGGER.debug("Updating role...");
		RoleDto updatedRole = roleService.getRole(idToBeUpdated);
		updatedRole.setRoleName(newRoleName);

		try {
			roleService.updateRole(updatedRole);
		} catch(Exception e) {
			response.sendError(response.SC_BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/role/deleteRole", method = RequestMethod.POST)
	public void deleteRole(@RequestParam(value = "idToBeDeleted") Integer idToBeDeleted,
						   HttpServletResponse response) throws Exception {

        LOGGER.debug("Deleting role...");

		try {
			roleService.deleteRole(idToBeDeleted);
		} catch(Exception e) {
			response.sendError(response.SC_BAD_REQUEST);
		}
	}



}
