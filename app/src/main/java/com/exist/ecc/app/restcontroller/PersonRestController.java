package com.exist.ecc.app.restcontroller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.exist.ecc.core.model.dto.PersonDto;
import com.exist.ecc.core.service.PersonService;
import com.exist.ecc.app.restcontroller.errors.Error;
import com.exist.ecc.core.service.exceptions.PersonNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestController
@RequestMapping("/rest")
public class PersonRestController {
    private PersonService personService;

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/person")
    public ResponseEntity getAllPersons() {
        return new ResponseEntity(personService.getAllPerson("id"), HttpStatus.OK);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity getPerson(@PathVariable("id") int id) {
        PersonDto person = personService.getPerson(id);
        return new ResponseEntity(person, HttpStatus.OK);
    }

    @PostMapping("/person")
    public ResponseEntity addPerson(@RequestBody PersonDto person) {
        personService.addPerson(person);
        return new ResponseEntity(person, HttpStatus.CREATED);
    }

    @PutMapping("/person")
    public ResponseEntity updatePerson(@RequestBody PersonDto person) {
        personService.updatePerson(person);
        return new ResponseEntity(person, HttpStatus.OK);
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity deletePerson(@PathVariable("id") int id) {
        personService.deletePerson(id);
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity personNotFound(PersonNotFoundException e) {
        int personId = e.getPersonId();
        Error error = new Error("No person found with id of " + personId);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

}
