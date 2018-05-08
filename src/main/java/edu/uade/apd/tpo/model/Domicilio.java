package edu.uade.apd.tpo.model;

public class Domicilio {

    private Long id;
    private String calle;
    private int numero;
    private String codPostal;
    private String localidad;
    private String provincia;
    private ZonaEnvio zona;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
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
}
