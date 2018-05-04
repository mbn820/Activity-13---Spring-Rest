package com.exist.ecc.app;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import com.exist.ecc.core.service.PersonService;
import com.exist.ecc.core.model.dto.PersonDto;
import com.exist.ecc.core.service.RoleService;
import com.exist.ecc.core.model.dto.RoleDto;
import com.exist.ecc.core.model.dto.ContactDto;
import com.exist.ecc.core.model.dto.NameDto;
import com.exist.ecc.core.model.dto.AddressDto;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.apache.commons.collections.list.LazyList;
import org.apache.commons.collections.FactoryUtils;

public class AddPersonController extends SimpleFormController {
	private PersonService personService;
	private RoleService roleService;

	public AddPersonController() {
		setCommandClass(PersonDto.class);
		setCommandName("person");
		setFormView("AddPerson");
		setSuccessView("success");
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

		List<RoleDto> existingRoles = roleService.getAllRoles();

		referenceData.put("existingRoles", existingRoles);

		return referenceData;
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		PersonDto person = new PersonDto();
		person.setName( new NameDto() );
		person.setAddress( new AddressDto() );
		person.setContacts( LazyList.decorate(new ArrayList(), FactoryUtils.instantiateFactory(ContactDto.class)) );
		return person;
	}

	@Override
	protected ModelAndView onSubmit(Object command) throws Exception {
		PersonDto createdPerson = (PersonDto) command;

		ModelAndView result = new ModelAndView( getSuccessView() );
		result.addObject("createdPerson", createdPerson);

		return result;
	}
}
