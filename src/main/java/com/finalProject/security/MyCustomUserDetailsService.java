package com.finalProject.security;


import com.finalProject.model.AuthUser;
import com.finalProject.services.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyCustomUserDetailsService implements UserDetailsService {

    AuthUserService authUserService;

    @Autowired
    public MyCustomUserDetailsService(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @SuppressWarnings("unused")
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //String username = "oper";

        System.out.println("-----llamado a la seguridad----- " + userName + " ----------------");
        AuthUser authUser = authUserService.findUserByEmail(userName);
        //System.out.println(userr.toString());
        if (authUser != null) {
            User.UserBuilder builder = User
                    .withUsername(userName)
                    .password(authUser.getPassword())
                    .roles(authUser.getType().toString());
            return builder.build();
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
    }
}
