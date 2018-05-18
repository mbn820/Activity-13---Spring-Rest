package com.exist.ecc.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.exist.ecc.core.model.dto.RoleDto;
import com.exist.ecc.core.service.RoleService;

@Component
public class RoleValidator implements Validator {

	@Autowired
	private RoleService roleService;

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@Override
	public boolean supports(Class cl) {
		return RoleDto.class.isAssignableFrom(cl);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleName", "roleName.required", "Required Field");

		RoleDto role = (RoleDto) target;

		if ( roleService.roleAlreadyExists(role) ) {
			errors.rejectValue("roleName", "roleName.alreadyExist");
		}

	}

}
