package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.FacturaDao;
import edu.uade.apd.tpo.entity.FacturaEntity;
import edu.uade.apd.tpo.entity.TransaccionEntity;
import edu.uade.apd.tpo.repository.stub.FacturaStub;

import java.util.Date;

public class Factura {

    private Long id;
    private Date fecha;
    private FacturaTipo tipo;
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
        entity.setCostoEnvio(costoEnvio);
        entity.setTotal(total);
        return entity;
    }

    public static Factura fromStub(FacturaStub stub) {

        Factura f = null;
        if (stub != null) {
            f = new Factura();
            f.setId(stub.getId());
            f.setFecha(stub.getFecha());
            f.setTipo(FacturaTipo.fromStub(stub.getTipo()));
            f.setCostoEnvio(stub.getCostoEnvio());
            f.setTotal(stub.getTotal());
        }
        return f;
    }

    public FacturaStub toStub() {
        FacturaStub entity = new FacturaStub();
        entity.setId(id);
        entity.setFecha(fecha);
        entity.setTipo(tipo.toStub());
        entity.setCostoEnvio(costoEnvio);
        entity.setTotal(total);
        return entity;
    }
}
