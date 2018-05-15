package com.exist.ecc.app.controller;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.io.File;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletResponse;
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
	private static final String destinationDir = "/home/mnunez/Desktop/Uploads/";

    @Autowired
    private PersonService personService;

    @Autowired
    private RoleService roleService;

	@Autowired
	private FileUploadValidator fileUploadValidator;

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

	public void setFileUploadValidator(FileUploadValidator fileUploadValidator) {
		this.fileUploadValidator = fileUploadValidator;
	}

	@RequestMapping(value = "/fileUploadForm.htm", method = RequestMethod.GET)
	public String loadFileUploadForm() {
		return "FileUpload";
	}

	@RequestMapping(value = "/fileUpload.htm", method = RequestMethod.POST)
	public String processUpload(@ModelAttribute("fileUpload") @Validated FileUpload fileUpload,
							    BindingResult result) throws Exception {

		fileUploadValidator.validate(fileUpload, result);
		if ( result.hasErrors() ) {
			return "FileUpload";
		}

		MultipartFile multipartFile = fileUpload.getMultipartFile();
		File destination = new File( destinationDir + multipartFile.getOriginalFilename() );
		multipartFile.transferTo(destination);

		PersonDto person = new XmlParser().convertToPersonDto(destination);

		fixRoles(person);

		personService.addPerson(person);

		return "redirect:/managePersons.htm";
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
