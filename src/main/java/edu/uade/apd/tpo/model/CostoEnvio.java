package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.entity.CostoEnvioEntity;

public class CostoEnvio {

    private Integer id;
    private ZonaEnvio zona;
    private Transportista transportista;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public float calcular() {
        return zona.getPrecio() * transportista.getPrecio();
    }

    public static CostoEnvio fromEntity(CostoEnvioEntity envioEntity) {
        CostoEnvio ce = null;
        if(envioEntity != null) {
            ce = new CostoEnvio();
            ce.setId(envioEntity.getId());
            ce.setZona(envioEntity.getZona());
            ce.setTransportista(envioEntity.getTransportista());
        }

        return ce;
    }

    public CostoEnvioEntity toEntity() {
        CostoEnvioEntity ce = new CostoEnvioEntity();
        ce.setId(id);
        ce.setZona(zona);
        ce.setTransportista(transportista);
        return ce;
    }
}