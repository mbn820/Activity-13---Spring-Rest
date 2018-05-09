package com.exist.ecc.core.dao;

import java.util.List;
import com.exist.ecc.core.model.Person;
import com.exist.ecc.core.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Order;

public class PersonDaoImpl implements PersonDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Integer addPerson(Person person) {
		return (Integer) getCurrentSession().save(person);
	}

	public void addOrUpdatePerson(Person person) {
		getCurrentSession().saveOrUpdate(person);
	}

	public Person getPerson(int id) {
		return (Person) getCurrentSession().get(Person.class, id);
	}

	public List<Person> getAllPerson(String orderBy) {
		return (List<Person>) getCurrentSession()
			.createCriteria(Person.class)
			.addOrder( Order.asc(orderBy) )
			.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
			.list();
	}

	public List<Person> getPersonsByLastName(String lastName, String orderBy, String orderType) {
		Criterion filterByLastName = Restrictions.ilike("name.lastName", lastName + "%");
		Order order = ( orderType.equals("desc") ) ? Order.desc(orderBy) : Order.asc(orderBy);

		return (List<Person>) getCurrentSession()
			.createCriteria(Person.class)
			.add(filterByLastName)
			.addOrder(order)
			.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
			.list();
	}

	public void updatePerson(Person person) {
		getCurrentSession().update(person);
	}

	public void deletePerson(int id) {
		getCurrentSession().delete( getPerson(id) );
	}

	public void deleteAllRecords() {
		List<Person> allPersons = getAllPerson("id");
		allPersons.forEach(person -> deletePerson(person.getId()));
	}
}
