package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.entity.DomicilioEntity;

public class Domicilio {

    private Integer id;
    private String calle;
    private Integer numero;
    private String codPostal;
    private String localidad;
    private String provincia;
    private ZonaEnvio zona;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public ZonaEnvio getZona() {
        return zona;
    }

    public void setZona(ZonaEnvio zona) {
        this.zona = zona;
    }

    public Domicilio() {

    }

    public Domicilio(String calle, int numero, String codPostal, String localidad, String provincia, ZonaEnvio zona) {
        this.calle = calle;
        this.numero = numero;
        this.codPostal = codPostal;
        this.localidad = localidad;
        this.provincia = provincia;
        this.zona = zona;
    }

    public DomicilioEntity toEntity() {
        DomicilioEntity entity = new DomicilioEntity();
        entity.setId(id);
        entity.setCalle(calle);
        entity.setNumero(numero);
        entity.setCodPostal(codPostal);
        entity.setLocalidad(localidad);
        entity.setProvincia(provincia);
        entity.setZona(zona);
        return entity;
    }

    public static Domicilio fromEntity(DomicilioEntity entity) {
        Domicilio dom = null;
        if (entity != null) {
            dom = new Domicilio();
            dom.setId(entity.getId());
            dom.setCalle(entity.getCalle());
            dom.setNumero(entity.getNumero());
            dom.setCodPostal(entity.getCodPostal());
            dom.setLocalidad(entity.getLocalidad());
            dom.setProvincia(entity.getProvincia());
            dom.setZona(entity.getZona());
        }

        return dom;
    }
}
