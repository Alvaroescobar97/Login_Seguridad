package com.finalProject.services;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalProject.dtos.LoginDTO;
import com.finalProject.model.AuthUser;
import com.finalProject.repository.AuthUserRepository;
import com.finalProject.security.PBKDF2Algorithm;

@Service
public class AuthUserService {

	@Autowired
	private AuthUserRepository userReposotory;
	@Autowired
	private PBKDF2Algorithm pbkdf2Algorithm;

	public AuthUser createUser(AuthUser authUser) throws NoSuchAlgorithmException, InvalidKeySpecException {
		// Aqui encriptar contraseña
		AuthUser authUserToSave = new AuthUser();
		authUserToSave.setNombre(authUser.getNombre());
		authUserToSave.setCorreo(authUser.getCorreo());
		// authUserToSave.setPassword(pbkdf2Algorithm.getEncryptedPassword(authUser.getPassword(),
		// pbkdf2Algorithm.generateSalt()));
		authUserToSave.setPassword(pbkdf2Algorithm.getEncryptedPassword(authUser.getPassword(), pbkdf2Algorithm.SALT));
		authUserToSave.setType(authUser.getType());

		return userReposotory.save(authUserToSave);
	}

	public AuthUser deleteUser(AuthUser authUser) {
		AuthUser authUserToDelete = null;
		if (userReposotory.findById(authUser.getId()).isPresent()) {
			userReposotory.delete(authUser);
			authUserToDelete = authUser;
		}
		return authUserToDelete;
	}

	public List<AuthUser> findAllUsers() {
		return userReposotory.findAll();
	}

	public AuthUser findUserByEmail(String correo) {
		return userReposotory.findByCorreo(correo);
	}

	public AuthUser findUserByName(String name) {
		return userReposotory.findByNombre(name);
	}

	public Boolean login(LoginDTO loginForm) throws NoSuchAlgorithmException, InvalidKeySpecException {
		Boolean logged = false;
		AuthUser user = userReposotory.findByCorreo(loginForm.getCorreo());
		logged = pbkdf2Algorithm.authenticate(loginForm.getPassword(), user.getPassword(), pbkdf2Algorithm.SALT);
		return logged;
	}

	public AuthUser updateUser(AuthUser authUser) throws NoSuchAlgorithmException, InvalidKeySpecException {
		AuthUser authUserToUpdate = null;
		if (userReposotory.findById(authUser.getId()).isPresent()) {
			// authUser.setPassword(pbkdf2Algorithm.getEncryptedPassword(authUser.getPassword(),
			// pbkdf2Algorithm.generateSalt()));
			authUser.setPassword(pbkdf2Algorithm.getEncryptedPassword(authUser.getPassword(), pbkdf2Algorithm.SALT));
			userReposotory.save(authUser);
			authUserToUpdate = new AuthUser();
			authUserToUpdate.setId(authUser.getId());
			authUserToUpdate.setNombre(authUser.getNombre());
			authUserToUpdate.setCorreo(authUser.getCorreo());
			authUserToUpdate.setPassword(authUser.getPassword());
			authUserToUpdate.setType(authUser.getType());
		}
		return authUserToUpdate;
	}

}
