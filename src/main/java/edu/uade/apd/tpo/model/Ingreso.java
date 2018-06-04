package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.IngresoDao;
import edu.uade.apd.tpo.entity.IngresoEntity;
import edu.uade.apd.tpo.repository.stub.IngresoStub;

public class Ingreso extends Movimiento {

    private MotivoIngreso motivo;

    public MotivoIngreso getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoIngreso motivo) {
        this.motivo = motivo;
    }

    public static Ingreso fromEntity(IngresoEntity entity) {
        Ingreso i = null;
        if (entity != null) {
            i = new Ingreso();
            i.setId(entity.getId());
            i.setFecha(entity.getFecha());
            i.setCantidad(entity.getCantidad());
            i.setMotivo(entity.getMotivo());
        }
        return i;
    }

    public static Ingreso fromStub(IngresoStub stub) {
        Ingreso i = null;
        if (stub != null) {
            i = new Ingreso();
            i.setId(stub.getId());
            i.setFecha(stub.getFecha());
            i.setCantidad(stub.getCantidad());
            i.setMotivo(MotivoIngreso.fromStub(stub.getMotivo()));
        }
        return i;
    }

    public IngresoEntity toEntity() {
        IngresoEntity entity = new IngresoEntity();
        entity.setId(id);
        entity.setFecha(fecha);
        entity.setCantidad(cantidad);
        entity.setMotivo(motivo);

        return entity;
    }

    public IngresoStub toStub() {
        IngresoStub entity = new IngresoStub();
        entity.setId(id);
        entity.setFecha(fecha);
        entity.setCantidad(cantidad);
        entity.setMotivo(motivo.toStub());

        return entity;
    }

    public void guardar() {
        IngresoDao.getInstance().save(this.toEntity());
    }

}
