package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.FacturaDao;
import edu.uade.apd.tpo.entity.FacturaEntity;

import java.util.Date;

public class Factura {

    private Integer id;
    private Date fecha;
    private TipoFactura tipo;
    private Pedido pedido;
    private Float total;
    private CostoEnvio costoEnvio;
    private Float impuestos;
    private Transaccion transaccion;

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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Float getTotal() {

        this.total = new Float(0);

        for (ItemPedido item : this.pedido.getItems()) {
            this.total += item.getSubtotal();
        }

        return total + (total * this.impuestos) + this.costoEnvio.calcular();
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

    public void guardar() {
        FacturaDao.getInstance().save(this.toEntity());
    }

    public FacturaEntity toEntity() {
        FacturaEntity entity = new FacturaEntity();
        entity.setId(id);
        entity.setFecha(fecha);
        entity.setTipo(tipo);
        entity.setPedido(pedido != null ? pedido.toEntity() : null);
        entity.setTotal(total);
        entity.setCostoEnvio(costoEnvio != null ? costoEnvio.toEntity() : null);
        entity.setImpuestos(impuestos);
        entity.setTransaccion(transaccion != null ? transaccion.toEntity() : null);
        return entity;
    }

    public static Factura fromEntity(FacturaEntity entity) {
        Factura factura = null;
        if (entity != null) {
            factura = new Factura();
            factura.setId(entity.getId());
            factura.setFecha(entity.getFecha());
            factura.setTipo(entity.getTipo());
            factura.setPedido(Pedido.fromEntity(entity.getPedido()));
            factura.setTotal(entity.getTotal());
            factura.setCostoEnvio(CostoEnvio.fromEntity(entity.getCostoEnvio()));
            factura.setImpuestos(entity.getImpuestos());
            factura.setTransaccion(Transaccion.fromEntity(entity.getTransaccion()));
        }

        return factura;
    }
}
