package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.FacturaDao;

public class Factura {

    private static final float IMPUESTOS = 0.21f;

    private Long id;
    private char tipo;
    private Pedido pedido;
    private Float total;

    public Factura() {

    }

    public Factura(Pedido pedido) {
        this.pedido = pedido;
        this.tipo = calcularTipoFactura();
        this.total = calcularTotal();
    }

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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void guardar() {
        FacturaDao.getInstance().save(this);
    }

    private char calcularTipoFactura() {
        return 'A';
    }

    private Float calcularTotal() {
        return this.pedido.getPrecioBruto() * IMPUESTOS + this.pedido.getPrecioBruto();
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
