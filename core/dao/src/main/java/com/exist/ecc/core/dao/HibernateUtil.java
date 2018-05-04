package com.exist.ecc.core.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import com.exist.ecc.core.model.Address;


public class HibernateUtil {
	private static SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

	public Object transact(Operation operation) {
		sessionFactory = getSessionFactory();

		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Object result = null;

		try {
			transaction = session.beginTransaction();
			result = operation.execute(session);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		} finally {
			session.close();
		}

		return result;
	}


}
