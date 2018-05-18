package com.exist.ecc.app.validator;

import java.io.File;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import com.exist.ecc.core.model.dto.PersonDto;
import com.exist.ecc.core.model.FileUpload;
import com.exist.ecc.core.service.XmlParser;

@Component
public class FileUploadValidator implements Validator {
	private static final String destinationDir = "/home/mnunez/Desktop/Uploads/";

	@Override
	public boolean supports(Class cl) {
		return FileUpload.class.isAssignableFrom(cl);
	}

	@Override
	public void validate(Object target, Errors errors) {
		FileUpload fileUpload = (FileUpload) target;
		MultipartFile multipartFile = fileUpload.getMultipartFile();
		String fileName = multipartFile.getOriginalFilename();

		// check if empty
		if ( multipartFile.getSize() == 0 ) {
			errors.rejectValue("multipartFile", "multipartFile.empty", "Empty File, select file to upload");
			return;
		}

		// check if xml
		try {
			String lastFourChars = fileName.substring( fileName.length() - 4 );
			if ( !lastFourChars.equals(".xml") ) {
				errors.rejectValue("multipartFile", "multipartFile.invalid", "Invalid File, please upload files with .xml extension");
				return;
			}
		} catch(Exception e) {
			errors.rejectValue("multipartFile", "multipartFile.invalid", "Invalid File, please upload files with '.xml' extension");
			return;
		}

		// try parsing the xml
		PersonDto person = null;
		try {
			File destination = new File( destinationDir + multipartFile.getOriginalFilename() );
			multipartFile.transferTo(destination);
			person = new XmlParser().convertToPersonDto(destination);
		} catch(Exception e) {
			errors.rejectValue("multipartFile", "multipartFile.xml.parseError", "Something wrong with file, please check contents");
			return;
		}

		// ValidationUtils.invokeValidator(personValidator, person, errors);
	}

}
