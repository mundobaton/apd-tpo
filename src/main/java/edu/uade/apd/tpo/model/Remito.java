package edu.uade.apd.tpo.model;

import java.util.Date;

import edu.uade.apd.tpo.dao.impl.RemitoDao;

public class Remito {

    private Long id;
    private Date fecha;
    private Factura factura;


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

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }
    
    public void guardar() {
    		RemitoDao.getInstance().save(this);
    }
}
