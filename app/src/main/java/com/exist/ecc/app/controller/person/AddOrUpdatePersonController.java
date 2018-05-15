package com.exist.ecc.app.controller.person;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.exist.ecc.core.model.dto.*;
import com.exist.ecc.core.service.PersonService;
import com.exist.ecc.core.service.RoleService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.beans.propertyeditors.CustomDateEditor;

public class AddOrUpdatePersonController extends SimpleFormController {
	private PersonService personService;
	private RoleService roleService;

	public AddOrUpdatePersonController() {
		setCommandClass(PersonDto.class);
		setCommandName("person");
		setFormView("AddOrUpdatePerson");
		setSuccessView("redirect:/managePersons.htm");
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@Override
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor( Date.class, new CustomDateEditor(dateFormat, true) );
    }

	@Override
	protected Map referenceData(HttpServletRequest request) {
		Map referenceData = new HashMap();
		referenceData.put( "existingRoles", roleService.getAllRoles() );

		if ( request.getParameter("personId") == null ) {
			referenceData.put("requestType", "ADD PERSON");
			referenceData.put("submitLabel", "ADD");
		} else {
			referenceData.put("requestType", "UPDATE PERSON");
			referenceData.put("submitLabel", "SAVE CHANGES");
		}

		return referenceData;
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String idOfPersonToBeUpdated = request.getParameter("personId");

		if (idOfPersonToBeUpdated != null) {
			PersonDto personToBeUpdated = personService.getPerson( Integer.parseInt(idOfPersonToBeUpdated) );
			return personToBeUpdated;
		}

		PersonDto person = new PersonDto();
		person.setName( new NameDto() );
		person.setAddress( new AddressDto() );
		person.setRoles( new ArrayList<RoleDto>() );
		person.setContacts( new ArrayList<ContactDto>() );

		return person;
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		PersonDto createdPerson = (PersonDto) command;

		// get unbinded fields using getParameter
		String[] roleIds = request.getParameterValues("rolesParam");
		String[] cellphones = request.getParameterValues("Cellphone");
		String[] landlines = request.getParameterValues("Landline");
		String[] emails = request.getParameterValues("Email");
		List<RoleDto> chosenRoles = new ArrayList<RoleDto>();
		List<ContactDto> contacts = new ArrayList<ContactDto>();

		// fetch chosen roles from the db
		if (roleIds != null) {
			for (String roleId : roleIds) {
				chosenRoles.add( roleService.getRole(Integer.parseInt(roleId)) );
			}
		}

		if (cellphones != null) {
			for (String cellphoneDetails : cellphones) {
				contacts.add( new ContactDto("Cellphone", cellphoneDetails) );
			}
		}

		if (landlines != null) {
			for (String landlineDetails : landlines) {
				contacts.add( new ContactDto("Landline", landlineDetails) );
			}
		}

		if (emails != null) {
			for (String emailDetails : emails) {
				contacts.add( new ContactDto("Email", emailDetails) );
			}
		}

		// set remaining fields
		createdPerson.setRoles(chosenRoles);
		createdPerson.setContacts(contacts);

		if ( contactsHaveErrors(contacts) ) {
			Map modelMap = new HashMap();
			modelMap.put("person", createdPerson);
			errors.rejectValue("contacts", "contacts.invalidFormat");
			showForm(request, response, errors, modelMap);
		}

		if ( createdPerson.getId() == 0 ) {
			personService.addPerson(createdPerson);
		} else {
			personService.updatePerson(createdPerson);
		}

		// redirect to managePersons dashboard
		return new ModelAndView( getSuccessView() );
	}

	private boolean contactsHaveErrors(List<ContactDto> contacts) {
		String cellphonePattern = "\\d{10,11}";
		String landlinePattern = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
		String emailPattern = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

		if ( contacts.size() == 0 ) {
			return false;
		}

		for (ContactDto contact : contacts) {
			switch ( contact.getType() ) {
				case "Email" :
					if ( !contact.getDetail().matches(emailPattern) ) {
						return true;
					}
				case "Landline" :
					if ( !contact.getDetail().matches(landlinePattern) ) {
						return true;
					}
				case "Cellphone" :
					if ( !contact.getDetail().matches(cellphonePattern) ) {
						return true;
					}
			}
		}

		return false;
	}
}
