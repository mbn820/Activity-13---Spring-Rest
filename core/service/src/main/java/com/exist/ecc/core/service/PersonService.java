package com.exist.ecc.core.service;

import java.util.List;

import com.exist.ecc.core.model.dto.PersonDto;

public interface PersonService {
	public Integer addPerson(PersonDto person);
	public PersonDto addAndReturnPerson(PersonDto personDto);
	public Integer addOrUpdatePerson(PersonDto person);
	public PersonDto getPerson(int id);
	public List<PersonDto> getAllPerson(String orderBy);
	public List<PersonDto> getPersonsByLastName(String lastName, String orderBy, String orderType);
	public void updatePerson(PersonDto personDto);
	public boolean deletePerson(int id);
}
