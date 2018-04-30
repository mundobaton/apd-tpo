package edu.uade.apd.tpo.entity;

import edu.uade.apd.tpo.model.MedioPago;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "transacciones")
public class TransaccionEntity extends BaseEntity {

    @Id
    @Column(name = "transaccion_id")
    private long id;
    @Column(name = "importe")
    private float importe;
    @Column(name = "fecha")
    private Date fecha;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "medio_pago")
    private MedioPago medioPago;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public MedioPago getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(MedioPago medioPago) {
        this.medioPago = medioPago;
    }
}
