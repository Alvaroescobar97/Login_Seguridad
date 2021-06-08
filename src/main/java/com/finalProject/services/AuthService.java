package com.finalProject.services;

import com.finalProject.model.User;
import com.finalProject.repository.UserRepository;
import com.finalProject.security.PBKDF2Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@Service
public class AuthService {

    @Autowired
    private UserRepository userReposotory;
    @Autowired
    private PBKDF2Algorithm pbkdf2Algorithm;


    public User createUser(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Aqui encriptar contraseña
        User userToSave = new User();
        userToSave.setNombre(user.getNombre());
        userToSave.setCorreo(user.getCorreo());
        userToSave.setPassword(pbkdf2Algorithm.getEncryptedPassword(user.getPassword(), pbkdf2Algorithm.generateSalt()));
        userToSave.setType(user.getType());

        return userReposotory.save(userToSave);
    }


    public User findUserByName(String name) {
        return userReposotory.findByNombre(name);
    }

    public User findUserByEmail(String correo) {
        return userReposotory.findByCorreo(correo);
    }

    public List<User> findAllUsers() {
        return userReposotory.findAll();
    }

    public User updateUser(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        User userToUpdate = null;
        if (userReposotory.findById(user.getId()).isPresent()) {
            user.setPassword(pbkdf2Algorithm.getEncryptedPassword(user.getPassword(), pbkdf2Algorithm.generateSalt()));
            userReposotory.save(user);
            userToUpdate = new User();
            userToUpdate.setId(user.getId());
            userToUpdate.setNombre(user.getNombre());
            userToUpdate.setCorreo(user.getCorreo());
            userToUpdate.setPassword(user.getPassword());
            userToUpdate.setType(user.getType());
        }
        return userToUpdate;
    }


    public User deleteUser(User user) {
        User userToDelete = null;
        if (userReposotory.findById(user.getId()).isPresent()) {
            userReposotory.delete(user);
            userToDelete = user;
        }
        return userToDelete;
    }


}
