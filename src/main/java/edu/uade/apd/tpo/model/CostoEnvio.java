package edu.uade.apd.tpo.model;

public class CostoEnvio {

    private Long id;
    private ZonaEnvio zona;
    private Transportista transportista;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonaEnvio getZona() {
        return zona;
    }

    public void setZona(ZonaEnvio zona) {
        this.zona = zona;
    }

    public Transportista getTransportista() {
        return transportista;
    }

    public void setTransportista(Transportista transportista) {
        this.transportista = transportista;
    }
}
