package com.exist.ecc.core.model;

import java.util.Set;
import java.util.HashSet;
import java.util.Objects;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table (name = "ROLE")
public class Role {

	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "role_id_generator")
	@SequenceGenerator (name = "role_id_generator", sequenceName = "role_seq", allocationSize = 1)
	private int id;

	@Column (name = "role_name")
	private String roleName;

	@ManyToMany (mappedBy = "roles", fetch = FetchType.EAGER)
	private Set<Person> persons;

	public Role() {}
	public Role(String roleName) {
		this.roleName = roleName;
	}

	public int getId() {
		return id;
	}

	public String getRoleName() {
		return roleName;
	}

	public Set<Person> getPersons() {
		return persons;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

	public void addPerson(Person person) {
		if(persons == null) {
			persons = new HashSet<Person>();
		}
		persons.add(person);
		person.getRoles().add(this);
	}

	public void removePerson(Person person) {
		persons.remove(person);
	}

	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!this.getClass().equals(obj.getClass())) return false;

		Role otherRole = (Role)obj;
		if( this.roleName.toLowerCase().equals(otherRole.getRoleName().toLowerCase()) ) {
			return true;
		}
		return false;
	}

	public int hashCode() {
		return Objects.hash(roleName);
	}

	public String toString() {
		return roleName;
	}
}
