package com.finalProject.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalProject.dtos.LoginDTO;
import com.finalProject.model.AuthUser;
import com.finalProject.services.AuthUserService;

@RestController
@RequestMapping("/security/users")
public class AuthUserController {

	@Autowired
	public AuthUserService authUserService;

	@PostMapping("/add")
	public ResponseEntity<AuthUser> createUser(@RequestBody AuthUser authUser) {
		AuthUser authUserSaved = null;
		try {
			authUserSaved = authUserService.createUser(authUser);
			return new ResponseEntity<AuthUser>(authUserSaved, HttpStatus.OK);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<AuthUser>(authUserSaved, HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<AuthUser> deleteUser(@RequestBody AuthUser authUser) {
		AuthUser authUserDeleted = authUserService.deleteUser(authUser);
		if (authUserDeleted != null)
			return new ResponseEntity<AuthUser>(authUserDeleted, HttpStatus.OK);
		return new ResponseEntity<AuthUser>(authUserDeleted, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/all")
	public ResponseEntity<List<AuthUser>> getAllUsers() {
		List<AuthUser> allAuthUsers = authUserService.findAllUsers();
		return new ResponseEntity<List<AuthUser>>(allAuthUsers, HttpStatus.OK);
	}

	@GetMapping("/email/{correo}")
	public ResponseEntity<AuthUser> getUserByEmail(@PathVariable("correo") String correo) {
		AuthUser useFindByEmail = authUserService.findUserByEmail(correo);
		if (useFindByEmail != null)
			return new ResponseEntity<AuthUser>(useFindByEmail, HttpStatus.OK);
		return new ResponseEntity<AuthUser>(useFindByEmail, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/name/{nombre}")
	public ResponseEntity<AuthUser> getUserByName(@PathVariable("nombre") String nombre) {
		AuthUser useFindByName = authUserService.findUserByName(nombre);
		if (useFindByName != null)
			return new ResponseEntity<AuthUser>(useFindByName, HttpStatus.OK);
		return new ResponseEntity<AuthUser>(useFindByName, HttpStatus.NOT_FOUND);
	}

	@PostMapping("/login")
	public ResponseEntity<Boolean> login(@RequestBody LoginDTO loginForm) {
		Boolean userLogged = false;
		try {
			userLogged = authUserService.login(loginForm);
			return new ResponseEntity<Boolean>(userLogged, HttpStatus.OK);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Boolean>(userLogged, HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/update")
	public ResponseEntity<AuthUser> updateUser(@RequestBody AuthUser authUser) {
		AuthUser authUserUpdated = null;
		try {
			authUserUpdated = authUserService.updateUser(authUser);
			if (authUserUpdated != null)
				return new ResponseEntity<AuthUser>(authUserUpdated, HttpStatus.OK);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<AuthUser>(authUserUpdated, HttpStatus.NOT_FOUND);
	}
}
