package com.finalProject;

import com.finalProject.model.AuthUser;
import com.finalProject.model.Type;
import com.finalProject.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;

@SpringBootApplication
public class LoginApplication implements CommandLineRunner {

    @Autowired
    private AuthUserRepository userReposotory;

    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        userReposotory.deleteAll();

        AuthUser authUser1 = new AuthUser("Santiago", "test@test.com", "sanpass", Type.ADMIN);
        AuthUser authUser2 = new AuthUser("Elias", "test@test.com", "elipass", Type.ADMIN);
        AuthUser authUser3 = new AuthUser("Alvaro", "test@test.com", "alvpass", Type.ADMIN);

        this.userReposotory.save(authUser1);
        this.userReposotory.save(authUser2);
        this.userReposotory.save(authUser3);

		List<AuthUser> authUserList = userReposotory.findAll();
		for (AuthUser authUser :
                authUserList) {
			System.out.println(authUser.toString());
		}

    }


}
