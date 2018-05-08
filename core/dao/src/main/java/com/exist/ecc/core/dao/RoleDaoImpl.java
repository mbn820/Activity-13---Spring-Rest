package com.exist.ecc.core.dao;

import com.exist.ecc.core.model.Role;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Order;
import java.util.List;

public class RoleDaoImpl implements RoleDao {
	private HibernateUtil hibernateUtil;

	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}

	public Integer addRole(Role role) {
		return (Integer) hibernateUtil.transact(session -> session.save(role));
	}

	public Role getRole(int id) {
		return (Role) hibernateUtil.transact(session -> session.get(Role.class, id));
	}

	public Role getRoleByName(String roleName) {
		return (Role) hibernateUtil.transact(session ->
			session.createCriteria(Role.class)
				   .add( Restrictions.eq("roleName", roleName) )
				   .uniqueResult()
		);
	}

	public List<Role> getAllRoles() {
		return (List<Role>) hibernateUtil.transact(session ->
		 	session.createCriteria(Role.class)
				   .addOrder( Order.asc("id") )
				   // .setCacheable(true)
				   .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				   .list()
		);
	}

	public void updateRole(Role role) {
		hibernateUtil.transact( session -> { session.update(role); return null; } );
	}

	public void deleteRole(int id) {
		hibernateUtil.transact( session -> { session.delete(getRole(id)); return null; } );
	}

	public void deleteAllRoles() {
		List<Role> allRoles = getAllRoles();
		allRoles.forEach( role -> deleteRole(role.getId()) );
	}

}
