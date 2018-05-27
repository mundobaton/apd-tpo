package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.FacturaDao;
import edu.uade.apd.tpo.entity.FacturaEntity;
import edu.uade.apd.tpo.entity.TransaccionEntity;

import java.util.Date;

public class Factura {

    private Long id;
    private Date fecha;
    private FacturaTipo tipo;
    private Pedido pedido;
    private float costoEnvio;
    private static float IMPUESTOS = 0.21f;
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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public float getCostoEnvio() {
        return costoEnvio;
    }

    public void setCostoEnvio(float costoEnvio) {
        this.costoEnvio = costoEnvio;
    }

    public static float getImpuestos() {
        return IMPUESTOS;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

<<<<<<< HEAD
    public void guardar() {
        FacturaDao.getInstance().save(this.toEntity());
    }

    public static float getIMPUESTOS() {
        return IMPUESTOS;
    }

    public static void setIMPUESTOS(float iMPUESTOS) {
        IMPUESTOS = iMPUESTOS;
    }

    public static Factura fromEntity(FacturaEntity entity) {
=======
    public Transaccion getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

    public static Factura fromEntity(FacturaEntity entity, Transaccion transaccion) {
>>>>>>> develop2
        Factura f = null;
        if (entity != null) {
            f = new Factura();
            f.setId(entity.getId());
            f.setFecha(entity.getFecha());
            f.setTipo(entity.getTipo());
            f.setCostoEnvio(entity.getCostoEnvio());
            f.setTotal(entity.getTotal());
        }
        return f;
    }

    public FacturaEntity toEntity() {
        FacturaEntity entity = new FacturaEntity();
        entity.setId(id);
        entity.setFecha(fecha);
        entity.setTipo(tipo);
        entity.setPedido(pedido.toEntity());
        entity.setCostoEnvio(costoEnvio);
        entity.setTotal(total);
        return entity;
    }

    public void guardar() {
        FacturaDao.getInstance().save(this.toEntity(transaccion.toEntity()));
    }
}
