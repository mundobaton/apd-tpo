package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.RemitoDao;
import edu.uade.apd.tpo.entity.RemitoEntity;

import java.util.Date;

public class Remito {

    private Integer id;
    private Date fecha;
    private Factura factura;


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

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public void guardar() {
        RemitoDao.getInstance().save(this.toEntity());
    }

    public RemitoEntity toEntity() {
        RemitoEntity re = new RemitoEntity();
        re.setId(id);
        re.setFecha(fecha);
        re.setFactura(factura != null ? factura.toEntity() : null);
        return re;
    }

    public static Remito fromEntity(RemitoEntity entity) {
        Remito r = null;
        if(entity != null) {
            r = new Remito();
            r.setId(entity.getId());
            r.setFecha(entity.getFecha());
            r.setFactura(Factura.fromEntity(entity.getFactura()));
        }
        return r;
    }

}