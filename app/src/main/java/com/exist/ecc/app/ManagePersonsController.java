package com.exist.ecc.app;

import java.util.List;
import com.exist.ecc.core.service.PersonService;
import com.exist.ecc.core.model.dto.PersonDto;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class ManagePersonsController extends AbstractController {
	private PersonService personService;

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String lastNameFilter = request.getParameter("lastNameFilter");
		String orderBy = request.getParameter("orderBy");
		String orderType = request.getParameter("orderType");

		if (lastNameFilter == null) { lastNameFilter = ""; }
		if (orderBy == null) { orderBy = "id"; }
		if (orderType == null) { orderType = "asc"; }

		List<PersonDto> personList = personService.getPersonsByLastName(lastNameFilter, orderBy, orderType);

		ModelAndView model = new ModelAndView("ManagePersons");
		model.addObject("personList", personList);

		return model;
	}
}
