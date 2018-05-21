package edu.uade.apd.tpo.entity;

import edu.uade.apd.tpo.model.TipoFactura;

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
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "facturas")
public class FacturaEntity implements Persistible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "factura_id")
    private Integer id;
    @Column(name = "fecha")
    private Date fecha;
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
    @ManyToOne
    @JoinColumn(name = "transaccion_id")
    private TransaccionEntity transaccion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

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
}