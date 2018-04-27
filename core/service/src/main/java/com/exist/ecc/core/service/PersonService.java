package com.exist.ecc.core.service;

import com.exist.ecc.core.model.Person;
import com.exist.ecc.core.model.dto.PersonDto;
import com.exist.ecc.core.dao.PersonDao;
import com.exist.ecc.core.dao.HibernateDao;
import java.util.List;

public class PersonService {

	public Integer addPerson(PersonDto personDto) {
		Person personToBeAdded = new MapperUtil().mapToPerson(personDto);
		return new PersonDao().addPerson(personToBeAdded);
	}

	public PersonDto getPerson(int id) {
		Person person = new PersonDao().getPerson(id);
		return new MapperUtil().mapToPersonDto(person);
	}

	public List<PersonDto> getAllPerson(String orderBy) {
		List<Person> persons = new PersonDao().getAllPerson(orderBy);
		return new MapperUtil().mapToPersonDtoList(persons);
	}

	public List<PersonDto> getPersonsByLastName(String lastName) {
		List<Person> persons = new PersonDao().getPersonsByLastName(lastName);
		return new MapperUtil().mapToPersonDtoList(persons);
	}

	public void updatePerson(PersonDto personDto) {
		Person personToBeUpdated = new MapperUtil().mapToPerson(personDto);
		new PersonDao().updatePerson(personToBeUpdated);
	}

	public void deletePerson(int id) {
		new PersonDao().deletePerson(id);
	}

	public boolean databaseIsEmpty() {
		return getAllPerson("id").size() == 0;
	}
}
