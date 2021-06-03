package com.finalProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.finalProject.model.User;
import com.finalProject.repository.UserRepository;

@SpringBootApplication
public class LoginApplication implements CommandLineRunner{

	
	@Autowired
	private UserRepository userReposotory;
	
	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User("Santiago", "test@test");
		this.userReposotory.save(user);
		
	}

}
