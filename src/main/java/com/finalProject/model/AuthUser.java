package com.finalProject.model;

import com.finalProject.security.AuthUserValidation;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Entidad que representa un usuario
 */
@Document(collection = "Usuario")
public class AuthUser {

    /**
     * Es el identificador de un usuario.
     */
    @Id
    private String id;

    /**
     * Es el nombre de un usuario
     */
    private String nombre;

    /**
     * Es el correo de un usuario
     */
    private String correo;

    /**
     * Es la contraseña de un usuario
     */
    private String password;

    /**
     * Es el tipo de un usuario
     */
    private Type type;


    // Constructores
    public AuthUser(){}
    public AuthUser(String nombre, String correo, String password, Type type)  {
        super();
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.type = type;
    }


    /**
     * Devuelve el identificador de un usuario
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Cambia el identificador de un usuario
     * @param id - el nuevo indentificador
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre de un usuario
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Cambia el nombre de un usuario
     * @param nombre - el nuevo nombre de un usuario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el correo de un usuario
     * @return correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Cambia el correo de un usuario
     * @param correo - el nuevo correo de un usuario
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Devuelve la contraseña de un usuario
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Cambia la contraseña de un usuario
     * @param password - la nueva contraseña de un usuario
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Devuelve el tipo de un usuario
     * @return type
     */
    public Type getType() {
        return type;
    }

    /**
     * Cambia el tipo de un usuario
     * @param type - el nuevo tipo de un usuario
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Imprime los atributos de un usuario
     * @return
     */
    @Override
    public String toString() {
        return "AuthUser{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                '}';
    }
}
