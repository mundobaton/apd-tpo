package edu.uade.apd.tpo.model;

import java.util.Date;

public class Factura {

    private Long id;
    private Date fecha;
    private TipoFactura tipo;
    private Pedido pedido;
    private Float total;
    private CostoEnvio costoEnvio;
    private Float impuestos;
    private Transaccion transaccion;

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

    public TipoFactura getTipo() {
        return tipo;
    }

    public void setTipo(TipoFactura tipo) {
        this.tipo = tipo;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public CostoEnvio getCostoEnvio() {
        return costoEnvio;
    }

    public void setCostoEnvio(CostoEnvio costoEnvio) {
        this.costoEnvio = costoEnvio;
    }

    public Float getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(Float impuestos) {
        this.impuestos = impuestos;
    }

    public Transaccion getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }
}
