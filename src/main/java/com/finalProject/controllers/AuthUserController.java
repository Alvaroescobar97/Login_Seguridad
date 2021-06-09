package com.finalProject.controllers;

import com.finalProject.model.AuthUser;
import com.finalProject.security.AuthUserValidation;
import com.finalProject.services.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping("/security/users")
public class AuthUserController {

    @Autowired
    public AuthUserService authUserService;

    @PostMapping("/add")
    public ResponseEntity<AuthUser> createUser(@RequestBody AuthUser authUser) {
        AuthUser authUserSaved = null;
        try {
            authUserSaved = authUserService.createUser(authUser);
            return new ResponseEntity<AuthUser>(authUserSaved, HttpStatus.OK);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<AuthUser>(authUserSaved, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/name/{nombre}")
    public ResponseEntity<AuthUser> getUserByName(@PathVariable("nombre") String nombre) {
        AuthUser useFindByName = authUserService.findUserByName(nombre);
        if (useFindByName != null)
            return new ResponseEntity<AuthUser>(useFindByName, HttpStatus.OK);
        return new ResponseEntity<AuthUser>(useFindByName, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/email/{correo}")
    public ResponseEntity<AuthUser> getUserByEmail(@PathVariable("correo") String correo) {
        AuthUser useFindByEmail = authUserService.findUserByEmail(correo);
        if (useFindByEmail != null)
            return new ResponseEntity<AuthUser>(useFindByEmail, HttpStatus.OK);
        return new ResponseEntity<AuthUser>(useFindByEmail, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AuthUser>> getAllUsers() {
        List<AuthUser> allAuthUsers = authUserService.findAllUsers();
        return new ResponseEntity<List<AuthUser>>(allAuthUsers, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<AuthUser> updateUser(@RequestBody AuthUser authUser) {
        AuthUser authUserUpdated = null;
        try {
            authUserUpdated = authUserService.updateUser(authUser);
            if (authUserUpdated != null)
                return new ResponseEntity<AuthUser>(authUserUpdated, HttpStatus.OK);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<AuthUser>(authUserUpdated, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<AuthUser> deleteUser(@RequestBody AuthUser authUser) {
        AuthUser authUserDeleted = authUserService.deleteUser(authUser);
        if (authUserDeleted != null)
            return new ResponseEntity<AuthUser>(authUserDeleted, HttpStatus.OK);
        return new ResponseEntity<AuthUser>(authUserDeleted, HttpStatus.NOT_FOUND);
    }

}
