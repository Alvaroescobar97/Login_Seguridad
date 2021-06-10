package com.finalProject.repository;

import com.finalProject.model.AuthUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Entidad que representa un repositorio para acceder a un usuario
 */
@Repository
public interface AuthUserRepository extends MongoRepository<AuthUser, String> {

    AuthUser findByNombre(String name);
    AuthUser findByCorreo(String correo);

}
