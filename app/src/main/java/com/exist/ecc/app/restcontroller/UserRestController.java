package com.exist.ecc.app.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.exist.ecc.core.model.dto.UsersDto;
import com.exist.ecc.core.service.UserService;
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
public class UserRestController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity getAllUsers() {
        return new ResponseEntity(userService.getAllUsers(), HttpStatus.OK);
    }

	@GetMapping("/user/{id}")
	public ResponseEntity getUser(@PathVariable("id") int id) {
		return new ResponseEntity(userService.getUser(id), HttpStatus.OK);
	}

	@PostMapping("/user")
	public ResponseEntity addUser(@RequestBody UsersDto user) {
		userService.addUser(user);
		return new ResponseEntity(user, HttpStatus.OK);
	}

	@PutMapping("/user")
	public ResponseEntity updateUser(@RequestBody UsersDto user) {
		userService.updateUser(user);
		return new ResponseEntity(user, HttpStatus.OK);
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity deleteUser(@PathVariable("id") int id) {
		userService.deleteUser(id);
		return new ResponseEntity(id, HttpStatus.OK);
	}

}
