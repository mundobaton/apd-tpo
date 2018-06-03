package edu.uade.apd.tpo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
<<<<<<< HEAD
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
=======
import javax.persistence.Table;
>>>>>>> develop
import java.util.Date;

@Entity
@Table(name = "movimientos")
@Inheritance(strategy = InheritanceType.JOINED)
<<<<<<< HEAD
public class MovimientoEntity implements Serializable {
=======
public abstract class MovimientoEntity extends BaseEntity {
>>>>>>> develop

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movimiento_id")
    protected Long id;
    @Column(name = "fecha")
    protected Date fecha;
    @Column(name = "cantidad")
    protected int cantidad;

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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
<<<<<<< HEAD

=======
>>>>>>> develop
}
