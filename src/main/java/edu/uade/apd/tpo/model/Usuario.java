package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.entity.UsuarioEntity;

public class Usuario {

    protected Integer id;
    protected String email;
    protected String password;
    protected Rol rol;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public static Usuario fromEntity(UsuarioEntity entity) {
        Usuario u = null;
        if (entity != null) {
            u = new Usuario();
            u.setId(entity.getId());
            u.setEmail(entity.getEmail());
            u.setPassword(entity.getPassword());
            u.setRol(entity.getRol());
        }
        return u;
    }

    public UsuarioEntity toEntity() {
        UsuarioEntity u = new UsuarioEntity();
        u.setId(id);
        u.setEmail(email);
        u.setPassword(password);
        u.setRol(rol);
        return u;
    }
}
