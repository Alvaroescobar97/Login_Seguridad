package com.finalProject.repository;

import com.finalProject.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByNombre(String name);

    User findByCorreo(String correo);


}
