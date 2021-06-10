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

/**
 * Entidad que representa los servicios ofresidos para un usuario
 */
@Service
public class AuthUserService {

	/**
	 * Injecta el repositorio de un usuario
	 */
	@Autowired
	private AuthUserRepository userReposotory;

	/**
	 * Injecta el algorotmo de encriptación de la contraseña
	 * */
	@Autowired
	private PBKDF2Algorithm pbkdf2Algorithm;

	/**
	 * Permite crear un usuiario y lo agrega la base de datos
	 * @param authUser - el usuario a agregar
	 * @return el usuario creado
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
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

	/**
	 * Elimina un  usuario de la base de datos
	 * @param authUser - el usuario a eliminar
	 * @return el usuario eliminado
	 */
	public AuthUser deleteUser(AuthUser authUser) {
		AuthUser authUserToDelete = null;
		if (userReposotory.findById(authUser.getId()).isPresent()) {
			userReposotory.delete(authUser);
			authUserToDelete = authUser;
		}
		return authUserToDelete;
	}

	/**
	 * Devuelve una lista de usuarios desde la base de datos
	 * @return la lista de usuarios
	 */
	public List<AuthUser> findAllUsers() {
		return userReposotory.findAll();
	}

	/**
	 * Busca en la base de datos a un usuario a partir de un correo
	 * @param correo - un correo
	 * @return un usuario
	 */
	public AuthUser findUserByEmail(String correo) {
		return userReposotory.findByCorreo(correo);
	}

	/**
	 * Busca en la base de datos a un usuario a partir den nombre
	 * @param name - el nombre
	 * @return un usuario
	 */
	public AuthUser findUserByName(String name) {
		return userReposotory.findByNombre(name);
	}

	/**
	 * Verifica se un usuario se puede loguear
	 * @param loginForm - un usuario a loguear
	 * @return true, si el usuario se logueó; false, si no
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public Boolean login(LoginDTO loginForm) throws NoSuchAlgorithmException, InvalidKeySpecException {
		Boolean logged = false;
		AuthUser user = userReposotory.findByCorreo(loginForm.getCorreo());
		logged = pbkdf2Algorithm.authenticate(loginForm.getPassword(), user.getPassword(), pbkdf2Algorithm.SALT);
		return logged;
	}

	/**
	 * Actualiza un usuario en la base de datos.
	 * @param authUser - un usuario para actualizar
	 * @return el usuario actualizado
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
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
