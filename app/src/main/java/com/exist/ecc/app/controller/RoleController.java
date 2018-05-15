package com.exist.ecc.app.controller;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.exist.ecc.core.model.dto.PersonDto;
import com.exist.ecc.core.model.dto.RoleDto;
import com.exist.ecc.core.model.dto.ContactDto;
import com.exist.ecc.core.service.PersonService;
import com.exist.ecc.core.service.RoleService;
import org.springframework.validation.BindingResult;
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

@Controller
public class RoleController {

    @Autowired
    private PersonService personService;

    @Autowired
    private RoleService roleService;

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "/Rolesample.htm")
    public String sample() {
        return "Sample";
    }

	@RequestMapping(value = "/addRole/{roleName}")
	public String addRole() {
		return "redirect:/manageRoles2.htm";
	}

}
