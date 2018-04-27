package com.exist.ecc.core.dao;

import com.exist.ecc.core.model.Role;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Order;
import java.util.List;

public class RoleDao implements RoleDaoInterface {

	public Integer addRole(Role role) {
		return (Integer) new HibernateUtil().transact(session -> session.save(role));
	}

	public Role getRole(int id) {
		return (Role) new HibernateUtil().transact(session -> session.get(Role.class, id));
	}

	public List<Role> getAllRoles() {
		return (List<Role>) new HibernateUtil().transact(session ->
		 	session.createCriteria(Role.class)
				   .addOrder( Order.asc("id") )
				   .setCacheable(true)
				   .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				   .list()
		);
	}

	public void updateRole(Role role) {
		new HibernateUtil().transact( session -> { session.update(role); return null; } );
	}

	public void deleteRole(int id) {
		new HibernateUtil().transact( session -> { session.delete(getRole(id)); return null; } );
	}

	public void deleteAllRoles() {
		List<Role> allRoles = getAllRoles();
		allRoles.forEach( role -> deleteRole(role.getId()) );
	}

}
