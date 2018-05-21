package com.exist.ecc.app.restcontroller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.exist.ecc.core.model.dto.RoleDto;
import com.exist.ecc.core.service.RoleService;
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
		try {
			roleService.addRole(role);
		} catch(Exception e) {

		}
		return new ResponseEntity(role, HttpStatus.OK);
	}

	@PutMapping("/role")
	public ResponseEntity updateRole(@RequestBody RoleDto role) {
		try {
			roleService.updateRole(role);
		} catch(Exception e) {

		}
		return new ResponseEntity(role, HttpStatus.OK);
	}

	@DeleteMapping("/role/{id}")
	public ResponseEntity deleteRole(@PathVariable("id") int id) {
		try {
			roleService.deleteRole(id);
		} catch(Exception e) {

		}
		return new ResponseEntity(id, HttpStatus.OK);
	}

}
