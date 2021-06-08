package com.finalProject.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Usuario")
public class User {

    @Id
    private String id;
    //@NotBlank(groups = UserValidation.class, message = "El nombre no debe estar vacío.")
    private String nombre;
    //@NotBlank(groups = UserValidation.class, message = "El correo no debe estar vacío.")
    private String correo;
    //@NotBlank(groups = UserValidation.class, message = "La contraseña no debe estar vacía.")
    private String password;
    //@NotNull(groups = UserValidation.class, message = "El tipo no debe estar vacío.")
    private Type type;


    public User (){}

    public User(String nombre, String correo, String password, Type type)  {
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
        return "User{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                '}';
    }
}
