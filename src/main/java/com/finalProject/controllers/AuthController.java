package com.finalProject.controllers;

import com.finalProject.model.User;
import com.finalProject.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping("/security/users")
public class AuthController {

    @Autowired
    public AuthService authService;

    @PostMapping("/add")
    public ResponseEntity<User> createUserController( @RequestBody User user) {
        User userSaved = null;
        try {
            userSaved = authService.createUser(user);
            return new ResponseEntity<User>(userSaved, HttpStatus.OK);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<User>(userSaved, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/name/{nombre}")
    public ResponseEntity<User> getUserByName(@PathVariable("nombre") String nombre) {
        User useFindByName = authService.findUserByName(nombre);
        if (useFindByName != null)
            return new ResponseEntity<User>(useFindByName, HttpStatus.OK);
        return new ResponseEntity<User>(useFindByName, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/email/{correo}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("correo") String correo) {
        User useFindByEmail = authService.findUserByEmail(correo);
        if (useFindByEmail != null)
            return new ResponseEntity<User>(useFindByEmail, HttpStatus.OK);
        return new ResponseEntity<User>(useFindByEmail, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = authService.findAllUsers();
        return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User userUpdated = null;
        try {
            userUpdated = authService.updateUser(user);
            if (userUpdated != null)
                return new ResponseEntity<User>(userUpdated, HttpStatus.OK);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<User>(userUpdated, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<User> deleteUser(@RequestBody User user) {
        User userDeleted = authService.deleteUser(user);
        if (userDeleted != null)
            return new ResponseEntity<User>(userDeleted, HttpStatus.OK);
        return new ResponseEntity<User>(userDeleted, HttpStatus.NOT_FOUND);
    }

}
