package com.exist.ecc.app.controller.person;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import com.exist.ecc.core.service.PersonService;
import com.exist.ecc.core.service.RoleService;
import com.exist.ecc.core.service.XmlParser;
import com.exist.ecc.core.model.FileUpload;
import com.exist.ecc.core.model.dto.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class FileUploadController extends SimpleFormController {
	private static final String destinationDir = "/home/mnunez/Desktop/Uploads/";
	private PersonService personService;
	private RoleService roleService;

	public FileUploadController() {
		setCommandClass(FileUpload.class);
		setCommandName("fileUpload");
		setFormView("FileUpload");
		setSuccessView("redirect:/managePersons.htm");
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@Override
	protected Map referenceData(HttpServletRequest request) {
		Map referenceData = new HashMap();
		referenceData.put( "person", new PersonDto() );
		return referenceData;
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		FileUpload file = (FileUpload) command;

		MultipartFile multipartFile = file.getMultipartFile();
		File destination = new File( destinationDir + multipartFile.getOriginalFilename() );
		multipartFile.transferTo(destination);

		PersonDto person = new XmlParser().convertToPersonDto(destination);

		fixRoles(person);

		personService.addPerson(person);

		return new ModelAndView( getSuccessView() );
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
