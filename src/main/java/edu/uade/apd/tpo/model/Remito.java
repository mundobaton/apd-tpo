package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.RemitoDao;
import edu.uade.apd.tpo.entity.RemitoEntity;

import java.util.Date;

public class Remito {

    private Long id;
    private Date fecha;

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

    public RemitoEntity toEntity() {
        RemitoEntity entity = new RemitoEntity();
        entity.setId(id);
        entity.setFecha(fecha);
        return entity;
    }
}
