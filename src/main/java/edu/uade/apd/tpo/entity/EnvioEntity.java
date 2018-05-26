package edu.uade.apd.tpo.entity;

import java.io.Serializable;

import edu.uade.apd.tpo.model.Transportista;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "envios")
public class EnvioEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "envio_id")
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id")
    private DomicilioEntity domicilio;
    @Column(name = "transportista_id")
    @Enumerated(EnumType.ORDINAL)
    private Transportista transportista;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "remito_id")
    private RemitoEntity remito;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DomicilioEntity getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioEntity domicilio) {
        this.domicilio = domicilio;
    }

    public Transportista getTransportista() {
        return transportista;
    }

    public void setTransportista(Transportista transportista) {
        this.transportista = transportista;
    }

    public RemitoEntity getRemito() {
        return remito;
    }

    public void setRemito(RemitoEntity remito) {
        this.remito = remito;
    }


}
