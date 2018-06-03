package edu.uade.apd.tpo.entity;

<<<<<<< HEAD
import java.io.Serializable;

=======
>>>>>>> develop
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

<<<<<<< HEAD
    @Column(name = "motivo_id")
    @Enumerated(EnumType.ORDINAL)
    private MotivoIngreso motivo;

    public MotivoIngreso getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoIngreso motivo) {
        this.motivo = motivo;
    }

=======
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "motivo")
    private MotivoIngreso motivoIngreso;

    public MotivoIngreso getMotivoIngreso() {
        return motivoIngreso;
    }

    public void setMotivoIngreso(MotivoIngreso motivoIngreso) {
        this.motivoIngreso = motivoIngreso;
    }
>>>>>>> develop
}
