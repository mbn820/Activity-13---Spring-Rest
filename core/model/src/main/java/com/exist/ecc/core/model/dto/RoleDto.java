package com.exist.ecc.core.model.dto;

import java.util.Set;
import java.util.HashSet;
import java.util.Objects;

public class RoleDto {

	private int id;
	private String roleName;
	private Set<PersonDto> persons;

	public RoleDto() {}
	public RoleDto(String roleName) {
		this.roleName = roleName;
	}

	public int getId() {
		return id;
	}

	public String getRoleName() {
		return roleName;
	}

	public Set<PersonDto> getPersons() {
		return persons;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setPersons(Set<PersonDto> persons) {
		this.persons = persons;
	}

	public void addPerson(PersonDto person) {
		if(persons == null) {
			persons = new HashSet<PersonDto>();
		}
		persons.add(person);
		person.getRoles().add(this);
	}

	public void removePerson(PersonDto person) {
		persons.remove(person);
	}

	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!this.getClass().equals(obj.getClass())) return false;

		RoleDto otherRole = (RoleDto)obj;
		if( this.roleName.equals(otherRole.getRoleName()) ) {
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
