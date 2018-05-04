package com.exist.ecc.app;

import java.util.List;
import com.exist.ecc.core.service.RoleService;
import com.exist.ecc.core.model.dto.RoleDto;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class SpringFormTestController extends SimpleFormController {

	public SpringFormTestController() {
		setCommandClass(Employee.class);
		setCommandName("employee");
		setFormView("springFormTest");
		setSuccessView("springFormTest");
	}

	@Override
	protected ModelAndView onSubmit(Object command) throws Exception {
		Employee emp = (Employee) command;

		ModelAndView result = new ModelAndView();
		result.addObject("employee", emp);


		return result;
	}
}
