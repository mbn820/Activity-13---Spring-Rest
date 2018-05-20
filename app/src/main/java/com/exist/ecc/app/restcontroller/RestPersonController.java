package com.exist.ecc.app.restcontroller;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.exist.ecc.app.validator.PersonValidator;
import com.exist.ecc.core.model.dto.PersonDto;
import com.exist.ecc.core.model.dto.RoleDto;
import com.exist.ecc.core.model.dto.ContactDto;
import com.exist.ecc.core.model.dto.UsersDto;
import com.exist.ecc.core.service.PersonService;
import com.exist.ecc.core.service.RoleService;
import com.exist.ecc.core.service.UserService;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.BindingResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class RestPersonController {
    private PersonService personService;

    @Autowired
    private UserService userService;

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/rest/persons")
    public List listOfAllPersons() {
        return personService.getAllPerson("id");
    }

    @GetMapping("/rest/person/{id}")
    public ResponseEntity getPerson(@PathVariable("id") int id) {
        return new ResponseEntity(personService.getPerson(id), HttpStatus.OK);
    }

    @PostMapping("/rest/person")
    public ResponseEntity addPerson(@RequestBody PersonDto person) {
        personService.addPerson(person);
        return new ResponseEntity(person, HttpStatus.OK);
    }

    @DeleteMapping("rest/person/{id}")
    public ResponseEntity deletePerson(@PathVariable("id") int id) {
        personService.deletePerson(id);
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @GetMapping("rest/user/{id}")
    public UsersDto getUser(@PathVariable("id") int id) {
        return userService.getUser(id);
    }

    @PostMapping("rest/user")
    public void addUser(@RequestBody UsersDto user) {
        userService.addUser(user);
    }



}
