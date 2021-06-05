package com.finalProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.finalProject.model.User;
import com.finalProject.repository.UserRepository;

import java.util.List;

@SpringBootApplication
public class LoginApplication implements CommandLineRunner{

	
	@Autowired
	private UserRepository userReposotory;
	
	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		userReposotory.deleteAll();

		User user = new User("Santiago", "test@test.com");
		User user1 = new User("Elias", "test@test.com");
		User user2 = new User("Alvaro", "test@test.com");

		this.userReposotory.save(user);
		this.userReposotory.save(user1);
		this.userReposotory.save(user2);


		/*
		List<User> userList = userReposotory.findAll();
		for (User user:
			 userList) {
			System.out.println(user.toString());
		}

		 */

	}

}
