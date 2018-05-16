package com.exist.ecc.core.service;

import java.util.List;
import com.exist.ecc.core.model.Person;
import com.exist.ecc.core.model.Role;
import com.exist.ecc.core.model.Users;
import com.exist.ecc.core.model.dto.PersonDto;
import com.exist.ecc.core.model.dto.RoleDto;
import com.exist.ecc.core.model.dto.UsersDto;

public interface DtoMapper {
	public PersonDto mapToPersonDto(Person person);
	public Person mapToPerson(PersonDto personDto) ;
	public List<PersonDto> mapToPersonDtoList(List<Person> persons);
	public List<Person> mapToPersonList(List<PersonDto> personDtos);

	public RoleDto mapToRoleDto(Role role);
	public Role mapToRole(RoleDto roleDto);
	public List<RoleDto> mapToRoleDtoList(List<Role> roles);
	public List<Role> mapToRoleList(List<RoleDto> roleDtos);

	public UsersDto mapToUserDto(Users user);
	public Users mapToUser(UsersDto userDto);
	public List<UsersDto> mapToUserDtoList(List<Users> users);
	public List<Users> mapToUserList(List<UsersDto> userDtos);
}
