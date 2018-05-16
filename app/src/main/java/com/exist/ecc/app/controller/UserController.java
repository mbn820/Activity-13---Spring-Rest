package com.exist.ecc.app.controller;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.http.ResponseEntity;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@ModelAttribute("userRoles")
	public List<String> populateUserRoles() {
		List<String> roles = new ArrayList<String>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		return roles;
	}

	@RequestMapping(value = "/addUser.htm", method = RequestMethod.GET)
	public String loadAddUserForm(ModelMap modelMap) {
		modelMap.addAttribute( "user", new UsersDto() );
		return "AddUser";
	}

	@RequestMapping(value = "/addUserSubmit.htm", method = RequestMethod.POST)
	public String processFormSubmit(@ModelAttribute("user") UsersDto user) {
		userService.addUser(user);
		return "redirect:/manageUsers.htm";
	}

	@RequestMapping(value = "/manageUsers.htm", method = RequestMethod.GET)
	public String loadManageUsersForm(ModelMap modelMap) {
		modelMap.addAttribute( "allUsers", userService.getAllUsers() );
		return "ManageUsers";
	}

	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
		return "redirect:/manageUsers.htm";
	}

	@RequestMapping(value = "/updateUser/{id}", method = RequestMethod.GET)
	public String updateUser(@PathVariable int id, ModelMap modelMap) {
		modelMap.addAttribute( "user", userService.getUser(id) );
		return "AddUser";
	}

	@RequestMapping(value = "/updateUserSubmit.htm", method = RequestMethod.POST)
	public String updateUserSubmit(@ModelAttribute("user") UsersDto user) {
		userService.updateUser(user);
		return "redirect:/manageUsers.htm";
	}


}
