package com.exist.ecc.core.dao;

import com.exist.ecc.core.model.Person;
import java.util.List;

public interface PersonDao {
	public Integer addPerson(Person person);
	public void addOrUpdatePerson(Person person);
	public Person getPerson(int id);
	public List<Person> getAllPerson(String orderBy);
	public List<Person> getPersonsByLastName(String lastName, String orderBy, String orderType);
	public void deletePerson(int id);
	public void updatePerson(Person person);
}
