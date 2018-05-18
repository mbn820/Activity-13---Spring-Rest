package com.exist.ecc.core.service;

import java.util.List;
import com.exist.ecc.core.model.Person;
import com.exist.ecc.core.model.dto.PersonDto;
import com.exist.ecc.core.dao.PersonDao;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {
	private PersonDao personDao;
	private DtoMapper dtoMapper;

	@Autowired
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	@Autowired
	public void setDtoMapper(DtoMapper dtoMapper) {
		this.dtoMapper = dtoMapper;
	}

	public Integer addPerson(PersonDto personDto) {
		Person personToBeAdded = dtoMapper.mapToPerson(personDto);
		return personDao.addPerson(personToBeAdded);
	}

	public Integer addOrUpdatePerson(PersonDto personDto) {
		Person personToBeAddedOrUpdated = dtoMapper.mapToPerson(personDto);
		int personId = personToBeAddedOrUpdated.getId();

		if (personId == 0) {
			return personDao.addPerson(personToBeAddedOrUpdated);
		} else {
			personDao.updatePerson(personToBeAddedOrUpdated);
			return personId;
		}
	}

	@Transactional(readOnly = true)
	public PersonDto getPerson(int id) {
		Person person = personDao.getPerson(id);
		return dtoMapper.mapToPersonDto(person);
	}

	@Transactional(readOnly = true)
	public List<PersonDto> getAllPerson(String orderBy) {
		List<Person> persons = personDao.getAllPerson(orderBy);
		return dtoMapper.mapToPersonDtoList(persons);
	}

	@Transactional(readOnly = true)
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
