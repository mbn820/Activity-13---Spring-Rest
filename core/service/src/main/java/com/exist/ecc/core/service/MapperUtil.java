package com.exist.ecc.core.service;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import com.exist.ecc.core.model.Person;
import com.exist.ecc.core.model.Role;
import com.exist.ecc.core.model.dto.PersonDto;
import com.exist.ecc.core.model.dto.RoleDto;
import java.util.List;

public class MapperUtil {
	private static MapperFactory mapperFactory;

	private static void buildMapperFactory() {
		mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(Person.class, PersonDto.class);
		mapperFactory.classMap(Role.class, RoleDto.class);
	}

	public static MapperFacade getMapperFacade() {
		if(mapperFactory == null) { buildMapperFactory(); }
		return mapperFactory.getMapperFacade();
	}

	public PersonDto mapToPersonDto(Person person) {
		return getMapperFacade().map(person, PersonDto.class);
	}

	public Person mapToPerson(PersonDto personDto) {
		return getMapperFacade().map(personDto, Person.class);
	}

	public List<PersonDto> mapToPersonDtoList(List<Person> persons) {
		return getMapperFacade().mapAsList(persons, PersonDto.class);
	}

	public List<Person> mapToPersonList(List<PersonDto> personDtos) {
		return getMapperFacade().mapAsList(personDtos, Person.class);
	}

	public RoleDto mapToRoleDto(Role role) {
		return getMapperFacade().map(role, RoleDto.class);
	}

	public Role mapToRole(RoleDto roleDto) {
		return getMapperFacade().map(roleDto, Role.class);
	}

	public List<RoleDto> mapToRoleDtoList(List<Role> roles) {
		return getMapperFacade().mapAsList(roles, RoleDto.class);
	}

	public List<Role> mapToRoleList(List<RoleDto> roleDtos) {
		return getMapperFacade().mapAsList(roleDtos, Role.class);
	}
}
