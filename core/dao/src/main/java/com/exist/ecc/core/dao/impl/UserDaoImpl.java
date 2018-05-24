package com.exist.ecc.core.dao.impl;

import java.util.List;

import com.exist.ecc.core.dao.UserDao;
import com.exist.ecc.core.model.Users;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Users getUserByName(String userName) {
		return (Users) getCurrentSession()
			.createCriteria(Users.class)
			.add( Restrictions.eq("userName", userName) )
			.uniqueResult();
	}

	public Integer addUser(Users user) {
		return (Integer) getCurrentSession().save(user);
	}

	public Users getUser(int id) {
		return (Users) getCurrentSession().get(Users.class, id);
	}

	public List<Users> getAllUsers() {
		return (List<Users>) getCurrentSession()
			.createCriteria(Users.class)
			.addOrder( Order.asc("id") )
			.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
			.list();
	}

	public void deleteUser(int id) {
		getCurrentSession().delete( getUser(id) );
	}

	public void updateUser(Users user) {
		getCurrentSession().update(user);
	}
}
