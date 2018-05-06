package com.exist.ecc.app;

import javax.servlet.http.HttpServletRequest;
import com.exist.ecc.core.model.dto.*;
import com.exist.ecc.core.service.*;
import java.util.Arrays;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.sql.Date;

public class PersonBuilder {
	private HttpServletRequest request;

	public PersonBuilder(HttpServletRequest request) {
		this.request = request;
	}

	public NameDto processNameInput() {
		String firstName = request.getParameter("firstName");
		String middleName = request.getParameter("middleName");
		String lastName = request.getParameter("lastName");
		String suffix = request.getParameter("suffix");
		String title = request.getParameter("title");
		return new NameDto(firstName, middleName, lastName, suffix, title);
	}

	public AddressDto processAddressInput() throws Exception {
		int streetNumber = Integer.parseInt( request.getParameter("streetNumber") );
		String barangay = request.getParameter("barangay");
		String municipality = request.getParameter("municipality");
		String zipcode = request.getParameter("zipcode");

		if( !zipcode.matches("[0-9]{4}") ) {
			throw new Exception();
		}

		return new AddressDto(streetNumber, barangay, municipality, zipcode);
	}

	public Date processBirthDateInput() {
		String bday = request.getParameter("birthDate");
		return Date.valueOf(bday);
	}

	public Date processDateHiredInput() {
		String dHired = request.getParameter("dateHired");
		return Date.valueOf(dHired);
	}

	public boolean processCurrentlyEmployedInput() {
		return ( request.getParameter("currentlyEmployed").equals("True") );
	}

	public double processGwaInput() throws Exception {
		double gwa = Double.parseDouble( request.getParameter("gwa") );
		if (gwa < 1.0 || gwa > 5.0) {
			throw new Exception();
		}
		return gwa;
	}

	public List<ContactDto> processContactsInput() throws Exception {
		String phonePattern = "\\d{10,11}";
		String landlinePattern = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
		String emailPattern = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";


		String contactPhone = request.getParameter("contacts_phone");
		String contactLandline = request.getParameter("contacts_landline");
		String contactEmail = request.getParameter("contacts_email");

		if ( !contactPhone.matches(phonePattern) || !contactLandline.matches(landlinePattern) || !contactEmail.matches(emailPattern) ) {
			throw new Exception(); // todo : create custom exception
		}

		ContactDto phone = new ContactDto("phone", contactPhone);
		ContactDto landline = new ContactDto("landline", contactLandline);
		ContactDto email = new ContactDto("email", contactEmail);

		return new ArrayList<ContactDto>( Arrays.asList(phone, landline, email) );
	}

	public List<RoleDto> processRolesInput() {
		List<RoleDto> roles = new ArrayList<RoleDto>();
		String[] roleIds = request.getParameterValues("roles");
		if ( roleIds != null ) {
			for(String roleId : roleIds) {
				int id = Integer.parseInt(roleId);
				roles.add( new RoleServiceImpl().getRole(id) );
			}
		}
		return roles;
	}

	public PersonDto build() throws Exception {
		PersonDto person = new PersonDto();
		try {
			NameDto name = processNameInput();
			AddressDto address = processAddressInput();
			Date birthDate = processBirthDateInput();
			Date dateHired = processDateHiredInput();
			boolean currentlyEmployed = processCurrentlyEmployedInput();
			double gwa = processGwaInput();
			List<ContactDto> contacts = processContactsInput();
			List<RoleDto> roles = processRolesInput();
			person = new PersonDto(name, address, birthDate, dateHired,
								   currentlyEmployed, gwa, roles, contacts);
			// roles.forEach( role -> person.addRole(role) );
		} catch( Exception e ) {
			throw new Exception();
		}

		return person;
	}
}
