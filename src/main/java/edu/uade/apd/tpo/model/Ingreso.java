package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.entity.IngresoEntity;

public class Ingreso extends Movimiento {

    private MotivoIngreso motivoIngreso;

    public MotivoIngreso getMotivoIngreso() {
        return motivoIngreso;
    }

    public void setMotivoIngreso(MotivoIngreso motivoIngreso) {
        this.motivoIngreso = motivoIngreso;
    }

    public static Ingreso fromEntity(IngresoEntity entity) {
        Ingreso ingreso = null;
        if (entity != null) {
            ingreso.setId(entity.getId());
            ingreso.setMotivoIngreso(entity.getMotivoIngreso());
        }
        return ingreso;
    }

    public IngresoEntity toEntity() {
        IngresoEntity entity = new IngresoEntity();
        entity.setId(id);
        entity.setMotivoIngreso(motivoIngreso);
        return entity;
    }
}
