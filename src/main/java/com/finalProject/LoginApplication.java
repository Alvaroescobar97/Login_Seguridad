package com.finalProject;

import com.finalProject.model.Type;
import com.finalProject.model.User;
import com.finalProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class LoginApplication implements CommandLineRunner {


    @Autowired
    private UserRepository userReposotory;




    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        userReposotory.deleteAll();

        User user1 = new User("Santiago", "test@test.com", "sanpass", Type.ADMIN);
        User user2 = new User("Elias", "test@test.com", "elipass", Type.ADMIN);
        User user3 = new User("Alvaro", "test@test.com", "alvpass", Type.ADMIN);

        this.userReposotory.save(user1);
        this.userReposotory.save(user2);
        this.userReposotory.save(user3);

		List<User> userList = userReposotory.findAll();
		for (User user:
			 userList) {
			System.out.println(user.toString());
		}

    }



}
