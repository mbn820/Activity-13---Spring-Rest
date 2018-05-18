package com.exist.ecc.app.controller;

import java.util.List;
import com.exist.ecc.core.model.dto.ContactDto;
import com.exist.ecc.core.model.dto.PersonDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;

@Component
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

		if ( !person.getAddress().getZipcode().matches("[0-9]{4}") ) {
			errors.rejectValue("address.zipcode", "address.zipcode.invalidFormat");
		}

		Double gwa = person.getGwa();
		if (gwa != null) {
			if (gwa < 1 || gwa > 5) {
				errors.rejectValue("gwa", "gwa.invalidValue");
			}
		}

		validateContacts(person.getContacts(), errors);

	}

	public void validateContacts(List<ContactDto> contacts, Errors errors) {
		String cellphonePattern = "\\d{10,11}";
		String landlinePattern = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
		String emailPattern = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

		if ( contacts.size() == 0 ) {
			return;
		}

		for (ContactDto contact : contacts) {
			switch ( contact.getType() ) {
				case "Email" :
					if ( !contact.getDetail().matches(emailPattern) ) {
						errors.rejectValue("contacts", "contacts.email.invalid", "Invalid Email");
					}
					break;
				case "Landline" :
					if ( !contact.getDetail().matches(landlinePattern) ) {
						errors.rejectValue("contacts", "contacts.landline.invalid", "Invalid Landline");
					}
					break;
				case "Cellphone" :
					if ( !contact.getDetail().matches(cellphonePattern) ) {
						errors.rejectValue("contacts", "contacts.cellphone.invalid", "Invalid Cellphone");
					}
					break;
			}
}
	}

}
