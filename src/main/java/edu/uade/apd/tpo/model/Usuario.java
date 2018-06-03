package edu.uade.apd.tpo.model;

<<<<<<< HEAD
import edu.uade.apd.tpo.dao.UsuarioDao;
import edu.uade.apd.tpo.entity.UsuarioEntity;
import edu.uade.apd.tpo.repository.stub.UsuarioStub;

public class Usuario {
=======
import edu.uade.apd.tpo.dao.impl.UsuarioDao;
import edu.uade.apd.tpo.remote.TransformUtils;
import edu.uade.apd.tpo.repository.stub.UsuarioStub;

public class Usuario implements Stubeable<UsuarioStub> {
>>>>>>> develop

    protected Long id;
    protected String email;
    protected String password;
    protected Rol rol;

<<<<<<< HEAD
=======
    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

>>>>>>> develop
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

<<<<<<< HEAD
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario guardar() {
        return Usuario.fromEntity(UsuarioDao.getInstance().save(this.toEntity()));
    }

=======
>>>>>>> develop
    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

<<<<<<< HEAD
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

    public static Usuario fromStub(UsuarioStub stub) {
        Usuario u = null;
        if (stub != null) {
            u = new Usuario();
            u.setId(stub.getId());
            u.setEmail(stub.getEmail());
            u.setPassword(stub.getPassword());
            u.setRol(Rol.fromStub(stub.getRol()));
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

    public UsuarioStub toStub() {
        UsuarioStub stub = new UsuarioStub();
        stub.setId(id);
        stub.setEmail(email);
        stub.setPassword(password);
        stub.setRol(rol.toStub());
        return stub;
=======
    public void guardar() {
        UsuarioDao.getInstance().save(this);
    }

    @Override
    public UsuarioStub toStub() {
        return TransformUtils.to(this, UsuarioStub.class);
    }

    public static Usuario fromStub(UsuarioStub stub) {
        return TransformUtils.to(stub, Usuario.class);
>>>>>>> develop
    }
}
