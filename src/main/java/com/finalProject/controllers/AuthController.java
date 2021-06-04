package com.finalProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalProject.model.User;
import com.finalProject.services.AuthService;

@RestController
@RequestMapping("/security/")
public class AuthController {

	@Autowired
	public AuthService authService;

	@PostMapping
	public ResponseEntity<User> createUserController(@RequestBody User user) {
		User userSaved = authService.createUser(user);
		return new ResponseEntity<User>(userSaved, HttpStatus.OK);
	}

}
