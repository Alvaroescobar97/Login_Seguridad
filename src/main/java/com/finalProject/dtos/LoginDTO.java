package com.finalProject.dtos;

/**
 * Entidad que representa las credenciales de un usuario para loguearse
 */
public class LoginDTO {

	/**
	 * El correo de un usuario
	 */
	private String correo;

	/**
	 * la contraseña de un usuario
	 */
	private String password;

	/**
	 * Devuelve un correo
	 * @return correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * Devuelve una contraseña
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Cambia un correo
	 * @param correo - un nuevo correo
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * Cambia una contraseña
	 * @param password - una nueva contraseña
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
