package edu.uade.apd.tpo.model;

import java.util.List;

public class Factura {

    private Long id;
    private char tipo;
    private long numeroFactura;
    private List<Pedido> pedidos;

    public char getTipo() {
        return tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public long getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(long numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
