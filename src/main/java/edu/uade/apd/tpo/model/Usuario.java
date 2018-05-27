package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.UsuarioDao;
import edu.uade.apd.tpo.entity.UsuarioEntity;

public class Usuario {

    protected Long id;
    protected String email;
    protected String password;
    protected Rol rol;

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
        UsuarioDao.getInstance().save(this.toEntity());
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
