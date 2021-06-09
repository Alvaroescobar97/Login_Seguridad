package com.finalProject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.finalProject.model.AuthUser;
import com.finalProject.model.Type;
import com.finalProject.repository.AuthUserRepository;
import com.finalProject.services.AuthUserService;

@SpringBootApplication
public class LoginApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}

	@Autowired
	private AuthUserRepository userReposotory;

	@Autowired
	private AuthUserService userService;

	@Override
	public void run(String... args) throws Exception {

		userReposotory.deleteAll();

		AuthUser authUser1 = new AuthUser("Santiago", "santiago@test.com", "sanpass", Type.ADMIN);
		AuthUser authUser2 = new AuthUser("Elias", "elias@test.com", "elipass", Type.ADMIN);
		AuthUser authUser3 = new AuthUser("Alvaro", "alvaro@test.com", "alvpass", Type.ADMIN);

		// this.userReposotory.save(authUser1);
		// this.userReposotory.save(authUser2);
		// this.userReposotory.save(authUser3);

		this.userService.createUser(authUser1);
		this.userService.createUser(authUser2);
		this.userService.createUser(authUser3);

		List<AuthUser> authUserList = userReposotory.findAll();
		for (AuthUser authUser : authUserList) {
			System.out.println(authUser.toString());
		}

	}

}
