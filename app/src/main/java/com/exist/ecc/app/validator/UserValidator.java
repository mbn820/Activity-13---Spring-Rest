package com.exist.ecc.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.exist.ecc.core.model.dto.UsersDto;
import com.exist.ecc.core.service.UserService;

@Component
public class UserValidator implements Validator {
	public static final int MIN_PASSWORD_LENGTH = 6;

	@Autowired
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean supports(Class cl) {
		return UsersDto.class.isAssignableFrom(cl);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "userName.required", "Required Field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required", "Required Field");

		UsersDto user = (UsersDto) target;
		if ( userService.userNameAlreadyExists(user.getUserName()) ) {
			errors.rejectValue("userName", "userName.alreadyExists", "Username already taken!");
		}

		if ( user.getPassword().length() < MIN_PASSWORD_LENGTH ) {
			errors.rejectValue("password", "password.tooShort", "Password too short");
		}


	}

}
