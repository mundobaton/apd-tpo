package edu.uade.apd.tpo.entity;

import java.io.Serializable;
import java.util.Date;

import edu.uade.apd.tpo.model.FacturaTipo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "facturas")
public class FacturaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "factura_id")
    private Long id;
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "tipo_factura_id")
    @Enumerated(EnumType.ORDINAL)
    private FacturaTipo tipo;
    @Column(name = "costo_envio")
    private float costoEnvio;
    @Column(name = "total")
    private float total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public FacturaTipo getTipo() {
        return tipo;
    }

    public void setTipo(FacturaTipo tipo) {
        this.tipo = tipo;
    }

    public float getCostoEnvio() {
        return costoEnvio;
    }

    public void setCostoEnvio(float costoEnvio) {
        this.costoEnvio = costoEnvio;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

}
