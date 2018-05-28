package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.ProveedorDao;
import edu.uade.apd.tpo.entity.ProveedorEntity;
import edu.uade.apd.tpo.repository.stub.ProveedorStub;

public class Proveedor {
    private Long id;
    private String nombre;
    private String cuit;
    private String telefono;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void guardar() {
        ProveedorDao.getInstance().save(this.toEntity());
    }

    public static Proveedor fromEntity(ProveedorEntity entity) {
        Proveedor p = null;
        if (entity != null) {
            p = new Proveedor();
            p.setId(entity.getId());
            p.setNombre(entity.getNombre());
            p.setCuit(entity.getCuit());
            p.setTelefono(entity.getTelefono());
        }
        return p;
    }

    public static Proveedor fromStub(ProveedorStub stub) {
        Proveedor p = null;
        if (stub != null) {
            p = new Proveedor();
            p.setId(stub.getId());
            p.setNombre(stub.getNombre());
            p.setCuit(stub.getCuit());
            p.setTelefono(stub.getTelefono());
        }
        return p;
    }

    public ProveedorEntity toEntity() {
        ProveedorEntity entity = new ProveedorEntity();
        entity.setId(id);
        entity.setTelefono(telefono);
        entity.setNombre(nombre);
        entity.setCuit(cuit);
        return entity;
    }

    public ProveedorStub toStub() {
        ProveedorStub stub = new ProveedorStub();
        stub.setId(id);
        stub.setTelefono(telefono);
        stub.setNombre(nombre);
        stub.setCuit(cuit);
        return stub;
    }
}
