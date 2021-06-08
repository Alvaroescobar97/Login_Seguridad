package com.finalProject.model;

import com.finalProject.security.AuthUserValidation;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Document(collection = "Usuario")
public class AuthUser {

    @Id
    private String id;
    @NotBlank(groups = AuthUserValidation.class, message = "El nombre no debe estar vacío.")
    private String nombre;
    @NotBlank(groups = AuthUserValidation.class, message = "El correo no debe estar vacío.")
    private String correo;
    @NotBlank(groups = AuthUserValidation.class, message = "La contraseña no debe estar vacía.")
    private String password;
    @NotNull(groups = AuthUserValidation.class, message = "El tipo no debe estar vacío.")
    private Type type;


    public AuthUser(){}

    public AuthUser(String nombre, String correo, String password, Type type)  {
        super();
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.type = type;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

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
