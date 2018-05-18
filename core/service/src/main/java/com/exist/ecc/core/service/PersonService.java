package com.exist.ecc.core.service;

import com.exist.ecc.core.model.dto.PersonDto;
import java.util.List;

public interface PersonService {
	public Integer addPerson(PersonDto person);
	public Integer addOrUpdatePerson(PersonDto person);
	public PersonDto getPerson(int id);
	public List<PersonDto> getAllPerson(String orderBy);
	public List<PersonDto> getPersonsByLastName(String lastName, String orderBy, String orderType);
	public void updatePerson(PersonDto personDto);
	public void deletePerson(int id);
}
