package com.exist.ecc.core.service.impl;

import java.util.List;

import com.exist.ecc.core.model.Person;
import com.exist.ecc.core.model.Role;
import com.exist.ecc.core.model.Users;
import com.exist.ecc.core.model.dto.PersonDto;
import com.exist.ecc.core.model.dto.RoleDto;
import com.exist.ecc.core.model.dto.UsersDto;
import com.exist.ecc.core.service.DtoMapper;

import org.springframework.stereotype.Component;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Component
public class DtoMapperImpl implements DtoMapper {
	private static MapperFactory mapperFactory;

	private static void buildMapperFactory() {
		mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(Person.class, PersonDto.class);
		mapperFactory.classMap(Role.class, RoleDto.class);
		mapperFactory.classMap(Users.class, UsersDto.class);
	}

	public static MapperFacade getMapperFacade() {
		if(mapperFactory == null) { buildMapperFactory(); }
		return mapperFactory.getMapperFacade();
	}

	// PERSON/PERSONDTO

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

	// ROLE/ROLEDTO

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

	// USER/USERDTO

	public UsersDto mapToUserDto(Users user) {
		return getMapperFacade().map(user, UsersDto.class);
	}

	public Users mapToUser(UsersDto userDto) {
		return getMapperFacade().map(userDto, Users.class);
	}

	public List<UsersDto> mapToUserDtoList(List<Users> users) {
		return getMapperFacade().mapAsList(users, UsersDto.class);
	}

	public List<Users> mapToUserList(List<UsersDto> userDtos) {
		return getMapperFacade().mapAsList(userDtos, Users.class);
	}
}
