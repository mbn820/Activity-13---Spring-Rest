package com.exist.ecc.app;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.sql.Date;
import com.exist.ecc.core.model.dto.*;
import com.exist.ecc.core.service.PersonService;
import com.exist.ecc.core.service.RoleService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.validation.BindException;

public class AddPersonController extends SimpleFormController {
	private PersonService personService;
	private RoleService roleService;

	public AddPersonController() {
		setCommandClass(PersonDto.class);
		setCommandName("person");
		setFormView("AddPerson");
		setSuccessView("redirect:/managePersons.htm");
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@Override
	protected Map referenceData(HttpServletRequest request) {
		Map referenceData = new HashMap();
		referenceData.put( "existingRoles", roleService.getAllRoles() );
		return referenceData;
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		PersonDto person = new PersonDto();
		person.setName( new NameDto() );
		person.setAddress( new AddressDto() );
		person.setContacts( new ArrayList<ContactDto>() );
		person.setRoles( new ArrayList<RoleDto>() );

		return person;
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if ( errors.hasErrors() ) {
			ModelAndView result = new ModelAndView( "redirect:/addPerson.htm" );
			return result;
		}

		PersonDto createdPerson = (PersonDto) command;

		// get unbinded fields using getParameter
		String birthDateParam = request.getParameter("birthDateParam");
		String dateHiredParam = request.getParameter("dateHiredParam");
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
		createdPerson.setBirthDate( Date.valueOf(birthDateParam) );
		createdPerson.setDateHired( Date.valueOf(dateHiredParam) );
		createdPerson.setRoles(chosenRoles);
		createdPerson.setContacts(contacts);

		personService.addPerson(createdPerson);

		// redirect to managePersons dashboard
		ModelAndView result = new ModelAndView( getSuccessView() );
		return result;
	}
}
