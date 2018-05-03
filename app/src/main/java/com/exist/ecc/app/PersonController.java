package com.exist.ecc.app;

import java.util.List;
import com.exist.ecc.core.service.PersonService;
import com.exist.ecc.core.model.dto.PersonDto;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class PersonController extends AbstractController {
	private PersonService personService;

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<PersonDto> persons = personService.getAllPerson("id");

		ModelAndView model = new ModelAndView("ManagePersons");
		model.addObject("personList", persons);

		return model;
	}
}
