package com.finalProject.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Usuario")
public class User {
	
	@Id
	private String id;
	//@Indexed(unique=true, sparse=true)
	private String nombre;
	private String correo;
	
	public User(String nombre, String correo) {
		super();
		this.nombre = nombre;
		this.correo = correo;
	}

	public String getId() {
		return id;
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

	@Override
	public String toString() {
		return "User{" +
				"id='" + id + '\'' +
				", nombre='" + nombre + '\'' +
				", correo='" + correo + '\'' +
				'}';
	}
}
