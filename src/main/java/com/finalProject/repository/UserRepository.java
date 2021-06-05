package com.finalProject.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.finalProject.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByNombre(String name);
    User findByCorreo(String correo);


}