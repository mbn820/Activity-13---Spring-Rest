package com.exist.ecc.app.controller;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletResponse;
import com.exist.ecc.app.validator.UserValidator;
import com.exist.ecc.core.model.dto.UsersDto;
import com.exist.ecc.core.service.UserService;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
public class UserController {
	private UserService userService;
	private UserValidator userValidator;
	public static final String DEFAULT_ROLE = "ROLE_USER";
	public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setUserValidator(UserValidator userValidator) {
		this.userValidator = userValidator;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loadLoginPage(@RequestParam(value = "error", required = false) String error,
	                            ModelMap modelMap) {
		if (error != null) {
			modelMap.addAttribute("errorMsg", "Incorrect username or password");
		}
		return "Login";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String loadAccessDeniedPage() {
		return "403";
	}

	@ModelAttribute("userRoles")
	public List<String> populateUserRoles() {
		List<String> roles = new ArrayList<String>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		return roles;
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public String loadAddUserPage(ModelMap modelMap) {
		LOGGER.debug("Loading Add User Form...");

		UsersDto user = new UsersDto();
		user.setUserRole(DEFAULT_ROLE);
		
		modelMap.addAttribute( "user", user );
		return "AddUser";
	}

	@RequestMapping(value = "/addUserSubmit", method = RequestMethod.POST)
	public String processAddUserFormSubmit(@ModelAttribute("user") @Validated UsersDto user,
									BindingResult result, ModelMap modelMap) {

		LOGGER.debug("Processing user form submit...");
		userValidator.validate(user, result);
		if ( result.hasErrors() ) {
			return "AddUser";
		}

		String hashedPassword = new BCryptPasswordEncoder().encode( user.getPassword() );
		user.setPassword(hashedPassword);
		userService.addUser(user);

		modelMap.clear();
		return "redirect:/manageUsers";
	}

	@RequestMapping(value = "/manageUsers", method = RequestMethod.GET)
	public String loadManageUsersPage(ModelMap modelMap) {
		LOGGER.debug("Loading Manage Users Form...");
		modelMap.addAttribute( "allUsers", userService.getAllUsers() );
		return "ManageUsers";
	}

	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable int id) {
		LOGGER.debug("Deleting user...");
		userService.deleteUser(id);
		return "redirect:/manageUsers";
	}

	@RequestMapping(value = "/updateUser/{id}", method = RequestMethod.GET)
	public String loadUpdateUserPage(@PathVariable int id, ModelMap modelMap) {
		LOGGER.debug("Loading update user form...");
		UsersDto userToBeUpdated = userService.getUser(id);
		userToBeUpdated.setPassword("");
		modelMap.addAttribute("user", userToBeUpdated);
		return "AddUser";
	}

	@RequestMapping(value = "/updateUserSubmit", method = RequestMethod.POST)
	public String processUpdateUserFormSubmit(@ModelAttribute("user") UsersDto user,
											  BindingResult result) {
		LOGGER.debug("Updating user...");
		userValidator.validate(user, result);
		if ( result.hasErrors() ) {
			return "AddUser";
		}
		String hashedPassword = new BCryptPasswordEncoder().encode( user.getPassword() );
		user.setPassword(hashedPassword);
		userService.updateUser(user);
		return "redirect:/manageUsers";
	}


}
