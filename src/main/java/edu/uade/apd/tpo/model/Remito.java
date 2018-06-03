package edu.uade.apd.tpo.model;

<<<<<<< HEAD
import edu.uade.apd.tpo.dao.RemitoDao;
import edu.uade.apd.tpo.entity.RemitoEntity;
import edu.uade.apd.tpo.repository.stub.RemitoStub;

import java.util.Date;

=======
import java.util.Date;

import edu.uade.apd.tpo.dao.impl.RemitoDao;

>>>>>>> develop
public class Remito {

    private Long id;
    private Date fecha;
<<<<<<< HEAD
=======
    private Factura factura;

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
    public void guardar() {
        RemitoDao.getInstance().save(this.toEntity());
    }

    public static Remito fromEntity(RemitoEntity entity) {
        Remito r = null;
        if (entity != null) {
            r = new Remito();
            r.setId(entity.getId());
            r.setFecha(entity.getFecha());
        }
        return r;
    }

    public static Remito fromStub(RemitoStub stub) {
        Remito r = null;
        if (stub != null) {
            r = new Remito();
            r.setId(stub.getId());
            r.setFecha(stub.getFecha());
        }
        return r;
    }

    public RemitoEntity toEntity() {
        RemitoEntity entity = new RemitoEntity();
        entity.setId(id);
        entity.setFecha(fecha);
        return entity;
    }

    public RemitoStub toStub() {
        RemitoStub stub = new RemitoStub();
        stub.setId(id);
        stub.setFecha(fecha);
        return stub;
=======
    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }
    
    public void guardar() {
    		RemitoDao.getInstance().save(this);
>>>>>>> develop
    }
}
