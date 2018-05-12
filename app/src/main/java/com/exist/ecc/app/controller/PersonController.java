package com.exist.ecc.app.controller;

import java.util.List;
import com.exist.ecc.core.model.dto.PersonDto;
import com.exist.ecc.core.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PersonController {
    @Autowired
    private PersonService personService;

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(value = "/sample.htm")
    public String sample() {
        return "Sample";
    }

    @RequestMapping(value = "/managePersons2.htm", method = RequestMethod.GET)
    public ModelAndView loadManagePersons(@RequestParam(defaultValue = "") String lastNameFilter,
                                          @RequestParam(defaultValue = "id") String orderBy,
                                          @RequestParam(defaultValue = "asc") String orderType) {

        List<PersonDto> personList = personService.getPersonsByLastName(lastNameFilter, orderBy, orderType);
        ModelAndView mav = new ModelAndView("ManagePersons");
        mav.addObject("personList", personList);

        return mav;
    }
}
