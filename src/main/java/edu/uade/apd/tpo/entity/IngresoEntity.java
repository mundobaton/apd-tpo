package edu.uade.apd.tpo.entity;

import edu.uade.apd.tpo.model.MotivoIngreso;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "ingresos")
@PrimaryKeyJoinColumn(name = "ingreso_id")
public class IngresoEntity extends MovimientoEntity {

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "motivo")
    private MotivoIngreso motivoIngreso;

    public MotivoIngreso getMotivoIngreso() {
        return motivoIngreso;
    }

    public void setMotivoIngreso(MotivoIngreso motivoIngreso) {
        this.motivoIngreso = motivoIngreso;
    }
}
