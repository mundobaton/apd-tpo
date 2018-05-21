package edu.uade.apd.tpo.entity;

import edu.uade.apd.tpo.model.Transportista;
import edu.uade.apd.tpo.model.ZonaEnvio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "costos_envios")
public class CostoEnvioEntity implements Persistible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "costo_envio_id")
    private Integer id;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "zona")
    private ZonaEnvio zona;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "transportista")
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
}