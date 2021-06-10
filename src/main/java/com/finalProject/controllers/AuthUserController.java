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

/**
 * Entidad que representa el CRUD api rest para un usuario
 */
@RestController
@RequestMapping("/security/users")
public class AuthUserController {

	/**
	 * Injecta los servicios para un usuario
	 */
	@Autowired
	public AuthUserService authUserService;

	/**
	 * Permite agregar un nuevo usuario
	 */
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

	/**
	 * Permite eliminar un usuario
	 * @param authUser - un usuario a eliminar
	 * @return entidad que representa el cuerpo y el status de una petición
	 */
	@DeleteMapping("/delete")
	public ResponseEntity<AuthUser> deleteUser(@RequestBody AuthUser authUser) {
		AuthUser authUserDeleted = authUserService.deleteUser(authUser);
		if (authUserDeleted != null)
			return new ResponseEntity<AuthUser>(authUserDeleted, HttpStatus.OK);
		return new ResponseEntity<AuthUser>(authUserDeleted, HttpStatus.NOT_FOUND);
	}

	/**
	 * Devuelve la lista de usuarios.
	 * @return entidad que representa el cuerpo y el status de una petición
	 */
	@GetMapping("/all")
	public ResponseEntity<List<AuthUser>> getAllUsers() {
		List<AuthUser> allAuthUsers = authUserService.findAllUsers();
		return new ResponseEntity<List<AuthUser>>(allAuthUsers, HttpStatus.OK);
	}

	/**
	 * Devuelve un usuario buscado por el correo
	 * @param correo - el correo de un usuario
	 * @return entidad que representa el cuerpo y el status de una petición
	 */
	@GetMapping("/email/{correo}")
	public ResponseEntity<AuthUser> getUserByEmail(@PathVariable("correo") String correo) {
		AuthUser useFindByEmail = authUserService.findUserByEmail(correo);
		if (useFindByEmail != null)
			return new ResponseEntity<AuthUser>(useFindByEmail, HttpStatus.OK);
		return new ResponseEntity<AuthUser>(useFindByEmail, HttpStatus.NOT_FOUND);
	}

	/**
	 * Devuelve un usuario buscado por el nombre
	 * @param nombre - el nombre de un usuario
	 * @return entidad que representa el cuerpo y el status de una petición
	 */
	@GetMapping("/name/{nombre}")
	public ResponseEntity<AuthUser> getUserByName(@PathVariable("nombre") String nombre) {
		AuthUser useFindByName = authUserService.findUserByName(nombre);
		if (useFindByName != null)
			return new ResponseEntity<AuthUser>(useFindByName, HttpStatus.OK);
		return new ResponseEntity<AuthUser>(useFindByName, HttpStatus.NOT_FOUND);
	}

	/**
	 * Permite el loguéo de un usuario
	 * @param loginForm - credenciales de un usuario
	 * @return entidad que representa el cuerpo y el status de una petición
	 */
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

	/**
	 * Actualiza un usuario
	 * @param authUser - un usuario a actualizar
	 * @return entidad que representa el cuerpo y el status de una petición
	 */
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
