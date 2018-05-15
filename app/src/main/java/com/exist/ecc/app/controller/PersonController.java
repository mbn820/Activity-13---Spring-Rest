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

import com.exist.ecc.app.controller.person.PersonValidator;

@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PersonValidator personValidator;

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public void setPersonValidator(PersonValidator personValidator) {
        this.personValidator = personValidator;
    }

    @RequestMapping(value = "/managePersons.htm", method = RequestMethod.GET)
    public ModelAndView loadManagePersonsForm(@RequestParam(defaultValue = "") String lastNameFilter,
                                              @RequestParam(defaultValue = "id") String orderBy,
                                              @RequestParam(defaultValue = "asc") String orderType) {

        List<PersonDto> personList = personService.getPersonsByLastName(lastNameFilter, orderBy, orderType);
        ModelAndView mav = new ModelAndView("ManagePersons");
        mav.addObject("personList", personList);

        return mav;
    }

    @RequestMapping(value = "/deletePerson/{id}.htm", method = RequestMethod.GET)
    public String deletePerson(@PathVariable int id) {
        personService.deletePerson(id);
        return "redirect:/managePersons.htm";
    }

    @RequestMapping(value = "fullPersonDetails/{id}.htm")
    public String fullPersonDetails(@PathVariable int id, ModelMap modelMap) {
        modelMap.addAttribute( "person", personService.getPerson(id) );
        return "FullPersonDetailsForm";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor( Date.class, new CustomDateEditor(dateFormat, true) );
    }

    @RequestMapping(value = "/addOrUpdatePerson.htm", method = RequestMethod.GET)
    public ModelAndView loadAddOrUpdatePersonForm(@RequestParam(value = "personId", required = false) Integer idOfPersonToBeUpdated) {
        ModelAndView mav = new ModelAndView("AddOrUpdatePerson");
        if (idOfPersonToBeUpdated != null) {
            mav.addObject( "person", personService.getPerson(idOfPersonToBeUpdated) );
        } else {
            mav.addObject( "person", new PersonDto() );
        }
        return mav;
    }

    @ModelAttribute("existingRoles")
    public List<RoleDto> populateRoles() {
        return roleService.getAllRoles();
    }

    @RequestMapping(value = "/addOrUpdatePerson.htm", method = RequestMethod.POST)
    public String processFormSubmit(@ModelAttribute("person") @Validated PersonDto person,
                                    BindingResult result,
                                    @RequestParam(value = "rolesParam", required = false) List<Integer> idsOfChosenRoles,
                                    @RequestParam(value = "Email",      required = false) List<String> emails,
                                    @RequestParam(value = "Cellphone",  required = false) List<String> cellphones,
                                    @RequestParam(value = "Landline",   required = false) List<String> landlines) {

        personValidator.validate(person, result);
        if ( result.hasErrors() ) {
            return "AddOrUpdatePerson";
        }

        List<RoleDto> chosenRoles = new ArrayList<RoleDto>();
        List<ContactDto> contacts = new ArrayList<ContactDto>();

        if (idsOfChosenRoles != null) {
            idsOfChosenRoles.forEach( id -> chosenRoles.add(roleService.getRole(id)) );
        }
        if (emails != null) {
            emails.forEach( emailDetail -> contacts.add(new ContactDto("Email", emailDetail)) );
        }
        if (cellphones != null) {
            cellphones.forEach( cellphoneDetail -> contacts.add(new ContactDto("Cellphone", cellphoneDetail)) );
        }
        if (landlines != null) {
            landlines.forEach( landlineDetail -> contacts.add(new ContactDto("Landline", landlineDetail)) );
        }

        person.setRoles(chosenRoles);
        person.setContacts(contacts);

        // personValidator.validate(person, result);

        personService.addOrUpdatePerson(person);
        return "redirect:/managePersons2.htm";
    }

}
