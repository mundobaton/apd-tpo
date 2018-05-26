package edu.uade.apd.tpo.entity;

import java.io.Serializable;

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

    @Column(name = "motivo_id")
    @Enumerated(EnumType.ORDINAL)
    private MotivoIngreso motivo;

    public MotivoIngreso getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoIngreso motivo) {
        this.motivo = motivo;
    }

}
