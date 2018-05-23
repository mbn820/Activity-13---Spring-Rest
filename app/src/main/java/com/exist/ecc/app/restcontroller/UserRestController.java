package com.exist.ecc.app.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.exist.ecc.core.model.dto.UsersDto;
import com.exist.ecc.core.service.UserService;
import com.exist.ecc.app.restcontroller.errors.Error;
import com.exist.ecc.core.service.exceptions.UserNotFoundException;
import com.exist.ecc.core.service.exceptions.UserNameAlreadyTakenException;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/rest")
public class UserRestController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user", produces = "application/json")
    public ResponseEntity getAllUsers() {
        return new ResponseEntity(userService.getAllUsers(), HttpStatus.OK);
    }

	@GetMapping(value = "/user/{id}", produces = "application/json")
	public ResponseEntity<UsersDto> getUser(@PathVariable("id") int id) {
		return new ResponseEntity(userService.getUser(id), HttpStatus.OK);
	}

	@PostMapping(value = "/user", produces = "application/json")
	public ResponseEntity<UsersDto> addUser(@RequestBody UsersDto user) {
        String hashedPassword = new BCryptPasswordEncoder().encode( user.getPassword() );
		user.setPassword(hashedPassword);
		UsersDto createdUser = userService.addAndReturnUser(user);
		return new ResponseEntity(createdUser, HttpStatus.CREATED);
	}

	@PutMapping(value = "/user", produces = "application/json")
	public ResponseEntity<UsersDto> updateUser(@RequestBody UsersDto user) {
		userService.updateUser(user);
		return new ResponseEntity(user, HttpStatus.OK);
	}

	@DeleteMapping(value = "/user/{id}", produces = "application/json")
	public ResponseEntity deleteUser(@PathVariable("id") int id) {
		userService.deleteUser(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Error> userNotFound(UserNotFoundException e) {
        int userId = e.getUserId();
        Error error = new Error("No user found with id of " + userId);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNameAlreadyTakenException.class)
    public ResponseEntity<Error> userNameAlreadyTaken(UserNameAlreadyTakenException e) {
        String userName = e.getUserName();
        Error error = new Error("The username " + userName + " is already taken");
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

}
