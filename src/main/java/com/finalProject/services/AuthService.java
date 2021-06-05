package com.finalProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalProject.model.User;
import com.finalProject.repository.UserRepository;

import java.util.List;

@Service
public class AuthService {

	@Autowired
	private UserRepository userReposotory;

	public User createUser(User user) {
		// Aqui encriptar contraseña

		return userReposotory.save(user);
	}



	public User findUserByName(String name){
		return userReposotory.findByNombre(name);
	}

	public User findUserByEmail(String correo){
		return userReposotory.findByCorreo(correo);
	}

	public List<User> findAllUsers(){
		return userReposotory.findAll();
	}

	public User updateUser(User user){
		User userToUpdate = null;
		if (userReposotory.findById(user.getId()).isPresent()) {
			userReposotory.save(user);
			userToUpdate = user;
		}
		return userToUpdate;
	}


	public User deleteUser(User user) {
		User userToDelete = null;
		if (userReposotory.findById(user.getId()).isPresent()) {
			userReposotory.delete(user);
			userToDelete = user;
		}
		return  userToDelete;
	}






}
