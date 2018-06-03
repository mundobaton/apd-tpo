package edu.uade.apd.tpo.entity;

<<<<<<< HEAD
import java.io.Serializable;
import java.util.Date;

import edu.uade.apd.tpo.model.FacturaTipo;
=======
import edu.uade.apd.tpo.model.TipoFactura;
>>>>>>> develop

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
<<<<<<< HEAD
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "facturas")
public class FacturaEntity implements Serializable {
=======
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "facturas")
public class FacturaEntity extends BaseEntity {
>>>>>>> develop

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "factura_id")
    private Long id;
    @Column(name = "fecha")
    private Date fecha;
<<<<<<< HEAD
    @Column(name = "tipo_factura_id")
    @Enumerated(EnumType.ORDINAL)
    private FacturaTipo tipo;
    @Column(name = "costo_envio")
    private float costoEnvio;
    @Column(name = "total")
    private float total;
=======
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipo")
    private TipoFactura tipo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedido;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "costo_envio_id")
    private CostoEnvioEntity costoEnvio;
    @Column(name = "impuestos")
    private Float impuestos;
    @Column(name = "total")
    private Float total;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaccion_id")
    private TransaccionEntity transaccion;
>>>>>>> develop

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

<<<<<<< HEAD
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

=======
    public TipoFactura getTipo() {
        return tipo;
    }

    public void setTipo(TipoFactura tipo) {
        this.tipo = tipo;
    }

    public PedidoEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }

    public Float getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(Float impuestos) {
        this.impuestos = impuestos;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public TransaccionEntity getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(TransaccionEntity transaccion) {
        this.transaccion = transaccion;
    }

    public CostoEnvioEntity getCostoEnvio() {
        return costoEnvio;
    }

    public void setCostoEnvio(CostoEnvioEntity costoEnvio) {
        this.costoEnvio = costoEnvio;
    }
>>>>>>> develop
}
