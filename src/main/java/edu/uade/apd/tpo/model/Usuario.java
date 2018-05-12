package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.impl.UsuarioDao;
import edu.uade.apd.tpo.remote.TransformUtils;
import edu.uade.apd.tpo.repository.stub.UsuarioStub;

public class Usuario implements Stubeable<UsuarioStub> {

    protected Long id;
    protected String email;
    protected String password;
    protected Rol rol;

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public void guardar() {
        UsuarioDao.getInstance().save(this);
    }

    @Override
    public UsuarioStub toStub() {
        return TransformUtils.to(this, UsuarioStub.class);
    }

    public static Usuario fromStub(UsuarioStub stub) {
        return TransformUtils.to(stub, Usuario.class);
    }
}
