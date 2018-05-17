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

    @Autowired
    private PersonService personService;

    @Autowired
    private RoleService roleService;

	@Autowired
	private RoleValidator roleValidator;

    public static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

	public void setRoleValidator(RoleValidator roleValidator) {
		this.roleValidator = roleValidator;
	}

	@ModelAttribute("existingRoles")
	public List<RoleDto> populateRoles() {
		return roleService.getAllRoles();
	}


	@RequestMapping(value = "/manageRoles.htm", method = RequestMethod.GET)
	public String loadManageRolesForm(ModelMap modelMap) {
        LOGGER.info("Loading Manage Roles Form...");
		modelMap.addAttribute( "role", new RoleDto() );
		return "ManageRoles";
	}

	@RequestMapping(value = "/addRole.htm", method = RequestMethod.POST)
	public String processFormSubmit(@ModelAttribute("role") @Validated RoleDto roleToBeAdded,
									BindingResult result) {

        LOGGER.info("Processing role form submit...");
		roleValidator.validate(roleToBeAdded, result);
		if ( result.hasErrors() ) {
			return "ManageRoles";
		}

		try {
			roleService.addRole(roleToBeAdded);
		} catch(Exception e) {

		}

		return "redirect:/manageRoles.htm";
	}

	@RequestMapping(value = "/updateRole.htm", method = RequestMethod.POST)
	public void updateRole(@RequestParam(value = "idToBeUpdated") Integer idToBeUpdated,
						   @RequestParam(value = "newRoleName") String newRoleName,
						   HttpServletResponse response) throws Exception {

        LOGGER.info("Updating role...");
		RoleDto updatedRole = roleService.getRole(idToBeUpdated);
		updatedRole.setRoleName(newRoleName);

		try {
			roleService.updateRole(updatedRole);
		} catch(Exception e) {
			response.sendError(response.SC_BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/deleteRole.htm", method = RequestMethod.POST)
	public void deleteRole(@RequestParam(value = "idToBeDeleted") Integer idToBeDeleted,
						   HttpServletResponse response) throws Exception {

        LOGGER.info("Deleting role...");

		try {
			roleService.deleteRole(idToBeDeleted);
		} catch(Exception e) {
			response.sendError(response.SC_BAD_REQUEST);
		}
	}



}
