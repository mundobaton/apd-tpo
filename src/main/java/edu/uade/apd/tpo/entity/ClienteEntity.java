package edu.uade.apd.tpo.entity;

import edu.uade.apd.tpo.model.Rol;

public class ClienteEntity {

	private Long id;
	private String email;
	private String password;
	private Rol rol;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
