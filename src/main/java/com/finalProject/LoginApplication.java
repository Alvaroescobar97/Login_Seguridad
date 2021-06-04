package com.finalProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.finalProject.model.User;
import com.finalProject.repository.UserRepository;

@SpringBootApplication
public class LoginApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}

	@Autowired
	private UserRepository userReposotory;

	@Override
	public void run(String... args) throws Exception {
		User user = new User("Santiago", "test@test");
		this.userReposotory.save(user);
		User user1 = new User("Alvaro", "test@test");
		this.userReposotory.save(user1);
		System.out.println(user1.getNombre());
	}

}
