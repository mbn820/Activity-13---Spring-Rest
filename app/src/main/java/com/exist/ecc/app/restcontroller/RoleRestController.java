package com.exist.ecc.app.restcontroller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.exist.ecc.core.model.dto.RoleDto;
import com.exist.ecc.core.service.RoleService;
import com.exist.ecc.app.restcontroller.errors.Error;
import com.exist.ecc.core.service.exceptions.RoleAlreadyExistsException;
import com.exist.ecc.core.service.exceptions.RoleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestController
@RequestMapping("/rest")
public class RoleRestController {
    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/role")
    public ResponseEntity getAllRoles() {
        return new ResponseEntity(roleService.getAllRoles(), HttpStatus.OK);
    }

	@GetMapping("/role/{id}")
	public ResponseEntity getRole(@PathVariable("id") int id) {
		return new ResponseEntity(roleService.getRole(id), HttpStatus.OK);
	}

	@PostMapping("/role")
	public ResponseEntity addRole(@RequestBody RoleDto role) {
        Integer generatedId = roleService.addRole(role);
        return getRole(generatedId);
	}

	@PutMapping("/role")
	public ResponseEntity updateRole(@RequestBody RoleDto role) {
        roleService.updateRole(role);
		return new ResponseEntity(role, HttpStatus.OK);
	}

	@DeleteMapping("/role/{id}")
	public ResponseEntity deleteRole(@PathVariable("id") int id) {
        roleService.deleteRole(id);
		return new ResponseEntity(id, HttpStatus.OK);
	}

    @ExceptionHandler(RoleAlreadyExistsException.class)
    public ResponseEntity roleAlreadyExists(RoleAlreadyExistsException e) {
        String roleName = e.getRoleName();
        if (roleName == null) { roleName = ""; }
        Error error = new Error("The role " + roleName + " already exists in the database");
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity roleNotFound(RoleNotFoundException e) {
        Error error = new Error("Role not found in the database");
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

}
