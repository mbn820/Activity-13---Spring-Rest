package com.exist.ecc.app.restcontroller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.exist.ecc.core.model.dto.RoleDto;
import com.exist.ecc.core.service.RoleService;
import com.exist.ecc.app.restcontroller.errors.Error;
import com.exist.ecc.core.service.exceptions.RoleAlreadyExistsException;
import com.exist.ecc.core.service.exceptions.RoleNotFoundException;
import com.exist.ecc.core.service.exceptions.RoleIsAssignedException;
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

    @GetMapping(value = "/role", produces = "application/json")
    public ResponseEntity< List<RoleDto> > getAllRoles() {
        return new ResponseEntity(roleService.getAllRoles(), HttpStatus.OK);
    }

	@GetMapping(value = "/role/{id}", produces = "application/json")
	public ResponseEntity<RoleDto> getRole(@PathVariable("id") int id) {
		return new ResponseEntity(roleService.getRole(id), HttpStatus.OK);
	}

	@PostMapping(value = "/role", produces = "application/json")
	public ResponseEntity<RoleDto> addRole(@RequestBody RoleDto role) {
        RoleDto createdRole = roleService.addAndReturnRole(role);
        return new ResponseEntity(createdRole, HttpStatus.CREATED);
	}

	@PutMapping(value = "/role", produces = "application/json")
	public ResponseEntity<RoleDto> updateRole(@RequestBody RoleDto role) {
        roleService.updateRole(role);
		return new ResponseEntity(role, HttpStatus.OK);
	}

	@DeleteMapping(value = "/role/{id}", produces = "application/json")
	public ResponseEntity deleteRole(@PathVariable("id") int id) {
        roleService.deleteRole(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

    @ExceptionHandler(RoleAlreadyExistsException.class)
    public ResponseEntity<Error> roleAlreadyExists(RoleAlreadyExistsException e) {
        String roleName = e.getRoleName();
        Error error = new Error("The role " + roleName + " already exists in the database");
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Error> roleNotFound(RoleNotFoundException e) {
        int roleId = e.getRoleId();
        Error error = new Error("No role found with id of " + roleId);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleIsAssignedException.class)
    public ResponseEntity<Error> roleIsAssigned(RoleIsAssignedException e) {
        int roleId = e.getRoleId();
        Error error =
            new Error("Cannot delete role with id of " + roleId +
                      ". The role is currently assigned to 1 or more person");
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> error() {
        Error error = new Error("An error occured");
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

}
