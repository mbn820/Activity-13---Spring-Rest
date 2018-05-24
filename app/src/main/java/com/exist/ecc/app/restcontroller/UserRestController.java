package com.exist.ecc.app.restcontroller;

import java.util.List;

import com.exist.ecc.app.restcontroller.errors.Error;
import com.exist.ecc.core.model.dto.UsersDto;
import com.exist.ecc.core.service.UserService;
import com.exist.ecc.core.service.exceptions.UserNameAlreadyTakenException;
import com.exist.ecc.core.service.exceptions.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class UserRestController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users", produces = "application/json")
    public ResponseEntity< List<UsersDto> > getAllUsers() {
        return new ResponseEntity< List<UsersDto> >(userService.getAllUsers(), HttpStatus.OK);
    }

	@GetMapping(value = "/users/{id}", produces = "application/json")
	public ResponseEntity<UsersDto> getUser(@PathVariable("id") int id) {
		return new ResponseEntity<UsersDto>(userService.getUser(id), HttpStatus.OK);
	}

	@PostMapping(value = "/users", produces = "application/json")
	public ResponseEntity<UsersDto> addUser(@RequestBody UsersDto user) {
        String hashedPassword = new BCryptPasswordEncoder().encode( user.getPassword() );
		user.setPassword(hashedPassword);
		UsersDto createdUser = userService.addAndReturnUser(user);
		return new ResponseEntity<UsersDto>(createdUser, HttpStatus.CREATED);
	}

	@PutMapping(value = "/users", produces = "application/json")
	public ResponseEntity<UsersDto> updateUser(@RequestBody UsersDto user) {
		userService.updateUser(user);
		return new ResponseEntity<UsersDto>(user, HttpStatus.OK);
	}

	@DeleteMapping(value = "/users/{id}", produces = "application/json")
	public ResponseEntity deleteUser(@PathVariable("id") int id) {
		userService.deleteUser(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Error> userNotFound(UserNotFoundException e) {
        int userId = e.getUserId();
        Error error = new Error("No user found with id of " + userId);
        return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNameAlreadyTakenException.class)
    public ResponseEntity<Error> userNameAlreadyTaken(UserNameAlreadyTakenException e) {
        String userName = e.getUserName();
        Error error = new Error("The username " + userName + " is already taken");
        return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
    }

}
