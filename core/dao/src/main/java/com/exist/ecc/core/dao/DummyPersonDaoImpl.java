package com.exist.ecc.core.dao;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import com.exist.ecc.core.model.Person;
import com.exist.ecc.core.model.Role;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Order;
import com.exist.ecc.core.model.Name;

public class DummyPersonDaoImpl implements PersonDao {
	private List<Person> dummyDb = new ArrayList<>();

	public Integer addPerson(Person person) {
		return null;
	}

	public Person getPerson(int id) {
		return null;
	}

	public List<Person> getAllPerson(String orderBy) {
		return null;
	}

	public List<Person> getPersonsByLastName(String lastName) {
		return null;
	}

	public List<Person> getPersonsByLastName(String lastName, String orderBy, String orderType) {
		Person person1 = new Person();
		Person person2 = new Person();
		person1.setName( new Name("Juan", "dela", "Cruz", "jr", "") );
		person2.setName( new Name("Pedro", "Reyes", "Santos", "jr", "") );
		dummyDb.add(person1);
		dummyDb.add(person2);
		return dummyDb;
	}

	public void updatePerson(Person person) {

	}

	public void deletePerson(int id) {

	}

	public void deleteAllRecords() {

	}
}
