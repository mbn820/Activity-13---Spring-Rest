package com.exist.ecc.app.controller;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.io.File;
import java.text.SimpleDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletResponse;
import com.exist.ecc.app.validator.FileUploadValidator;
import com.exist.ecc.core.model.FileUpload;
import com.exist.ecc.core.model.dto.PersonDto;
import com.exist.ecc.core.model.dto.RoleDto;
import com.exist.ecc.core.model.dto.ContactDto;
import com.exist.ecc.core.service.PersonService;
import com.exist.ecc.core.service.RoleService;
import com.exist.ecc.core.service.XmlParser;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
	private PersonService personService;
    private RoleService roleService;
	private FileUploadValidator fileUploadValidator;
	private static final String DESTINATION_DIR = "/home/mnunez/Desktop/Uploads/";
	public static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

	@Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

	@Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

	@Autowired
	public void setFileUploadValidator(FileUploadValidator fileUploadValidator) {
		this.fileUploadValidator = fileUploadValidator;
	}

	@RequestMapping(value = "person/fileUploadForm", method = RequestMethod.GET)
	public String loadFileUploadForm() {
		LOGGER.debug("Loading file upload form...");
		return "person/FileUpload";
	}

	@RequestMapping(value = "person/fileUpload", method = RequestMethod.POST)
	public String processUpload(@ModelAttribute("fileUpload") @Validated FileUpload fileUpload,
							    BindingResult result) throws Exception {

		LOGGER.debug("Processing upload...");
		fileUploadValidator.validate(fileUpload, result);
		if ( result.hasErrors() ) {
			return "person/FileUpload";
		}

		MultipartFile multipartFile = fileUpload.getMultipartFile();
		File destination = new File( DESTINATION_DIR + multipartFile.getOriginalFilename() );
		multipartFile.transferTo(destination);

		PersonDto person = new XmlParser().convertToPersonDto(destination);

		fixRoles(person);

		Integer generatedId = personService.addPerson(person);

		return "redirect:/person/fullPersonDetails/" + generatedId;
	}

	private void fixRoles(PersonDto person) {
		List<RoleDto> personRoles = person.getRoles();
		List<RoleDto> existingRoles = roleService.getAllRoles();
		List<RoleDto> fixedRoles = new ArrayList<RoleDto>();

		if ( personRoles != null && !personRoles.isEmpty() ) {
			for (RoleDto personRole : personRoles) {
				RoleDto existingRole = roleService.getRoleByName( personRole.getRoleName() );
				if (existingRole != null) {
					fixedRoles.add(existingRole);
				} else {
					try {
						Integer generatedId = roleService.addRole( new RoleDto(personRole.getRoleName()) );
						fixedRoles.add( roleService.getRole(generatedId) );
					} catch(Exception e) {}
				}
			}
		}

		person.setRoles(fixedRoles);
	}


}
