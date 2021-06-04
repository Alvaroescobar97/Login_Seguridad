package com.finalProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalProject.model.User;
import com.finalProject.repository.UserRepository;

@Service
public class AuthService {

	@Autowired
	private UserRepository userReposotory;

	public User createUser(User user) {
		// Aqui encriptar contraseña

		return userReposotory.save(user);
	}

}
