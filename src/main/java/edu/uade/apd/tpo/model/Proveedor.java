package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.entity.ProveedorEntity;

public class Proveedor {

    private Integer id;
    private String nombre;
    private String cuit;
    private String telefono;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public ProveedorEntity toEntity() {
        ProveedorEntity pe = new ProveedorEntity();
        pe.setId(id);
        pe.setNombre(nombre);
        pe.setCuit(cuit);
        pe.setTelefono(telefono);
        return pe;
    }

    public static Proveedor fromEntity(ProveedorEntity entity) {
        Proveedor p = null;
        if(entity != null) {
            p = new Proveedor();
            p.setId(entity.getId());
            p.setNombre(entity.getNombre());
            p.setCuit(entity.getCuit());
            p.setTelefono(entity.getTelefono());
        }
        return p;
    }
}