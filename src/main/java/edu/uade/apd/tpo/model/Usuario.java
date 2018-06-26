package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.UsuarioDao;

public class Usuario {

    private Long id;
    private String legajo;
    private String password;
    private Rol rol;

    public Usuario(String legajo, String password, Rol rol) {
        this.legajo = legajo;
        this.password = password;
        this.rol = rol;
    }

    public Usuario() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
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

    public void guardar() {
        UsuarioDao.getInstance().save(this);
    }
}
