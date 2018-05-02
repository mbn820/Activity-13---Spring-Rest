package com.exist.ecc.core.service;

import com.exist.ecc.core.model.Person;
import com.exist.ecc.core.model.dto.PersonDto;
import com.exist.ecc.core.dao.PersonDao;
import com.exist.ecc.core.dao.PersonDaoImpl;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonServiceImpl implements PersonService {
	private PersonDao personDao;
	private DtoMapper dtoMapper;

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	public void setdtoMapper(DtoMapper dtoMapper) {
		this.dtoMapper = dtoMapper;
	}

	public Integer addPerson(PersonDto personDto) {
		Person personToBeAdded = dtoMapper.mapToPerson(personDto);
		return personDao.addPerson(personToBeAdded);
	}

	public PersonDto getPerson(int id) {
		Person person = personDao.getPerson(id);
		return dtoMapper.mapToPersonDto(person);
	}

	public List<PersonDto> getAllPerson(String orderBy) {
		List<Person> persons = personDao.getAllPerson(orderBy);
		return dtoMapper.mapToPersonDtoList(persons);
	}

	public List<PersonDto> getPersonsByLastName(String lastName, String orderBy, String orderType) {
		List<Person> persons = personDao.getPersonsByLastName(lastName, orderBy, orderType);
		return dtoMapper.mapToPersonDtoList(persons);
	}

	public void updatePerson(PersonDto personDto) {
		Person personToBeUpdated = dtoMapper.mapToPerson(personDto);
		personDao.updatePerson(personToBeUpdated);
	}

	public void deletePerson(int id) {
		personDao.deletePerson(id);
	}

	public boolean databaseIsEmpty() {
		return getAllPerson("id").size() == 0;
	}
}
