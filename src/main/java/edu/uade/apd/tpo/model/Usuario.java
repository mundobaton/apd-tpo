package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.entity.UsuarioEntity;

public class Usuario {

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void guardar() {

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
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(id);
        entity.setEmail(email);
        entity.setPassword(password);
        entity.setRol(rol);
        return entity;
    }
}
