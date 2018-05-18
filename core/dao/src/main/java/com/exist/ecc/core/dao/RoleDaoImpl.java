package com.exist.ecc.core.dao;

import java.util.List;
import com.exist.ecc.core.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class RoleDaoImpl implements RoleDao {
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Integer addRole(Role role) {
		return (Integer) getCurrentSession().save(role);
	}

	public Role getRole(int id) {
		return (Role) getCurrentSession().get(Role.class, id);
	}

	public Role getRoleByName(String roleName) {
		return (Role) getCurrentSession()
			.createCriteria(Role.class)
			.add( Restrictions.eq("roleName", roleName) )
			.uniqueResult();
	}

	public List<Role> getAllRoles() {
		return (List<Role>) getCurrentSession()
			.createCriteria(Role.class)
			.addOrder( Order.asc("id") )
			.setCacheable(true)
			.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
			.list();
	}

	public void updateRole(Role role) {
		getCurrentSession().merge(role);
	}

	public void deleteRole(int id) {
		getCurrentSession().delete( getRole(id) );
	}

	public void deleteAllRoles() {
		List<Role> allRoles = getAllRoles();
		allRoles.forEach( role -> deleteRole(role.getId()) );
	}

}
