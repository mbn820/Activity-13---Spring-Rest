package com.exist.ecc.app.controller.person;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.exist.ecc.core.model.dto.PersonDto;

public class PersonValidator implements Validator {

	@Override
	public boolean supports(Class cl) {
		return PersonDto.class.isAssignableFrom(cl);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name.firstName", "name.firstName.required", "Required Field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name.middleName", "name.middleName.required", "Required Field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name.lastName", "name.lastName.required", "Required Field");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.streetNumber", "address.streetNumber.required", "Required Field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.barangay", "address.barangay.required", "Required Field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.municipality", "address.municipality.required", "Required Field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.zipcode", "address.zipcode.required", "Required Field");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthDate", "birthDate.required", "Required Field");


		PersonDto person = (PersonDto) target;
		if (person.getDateHired() == null) {
			errors.rejectValue("dateHired", "dateHired.required");
		}

		if ( !person.getAddress().getZipcode().matches("[0-9]{4}") ) {
			errors.rejectValue("address.zipcode", "address.zipcode.invalidFormat");
		}

	}

}
