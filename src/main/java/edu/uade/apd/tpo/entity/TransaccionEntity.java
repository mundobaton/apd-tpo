package edu.uade.apd.tpo.entity;

<<<<<<< HEAD
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import edu.uade.apd.tpo.model.Factura;
=======
>>>>>>> develop
import edu.uade.apd.tpo.model.MedioPago;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
<<<<<<< HEAD

@Entity
@Table(name = "transacciones")
public class TransaccionEntity implements Serializable {
=======
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "transacciones")
public class TransaccionEntity extends BaseEntity {

>>>>>>> develop
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaccion_id")
    private Long id;
    @Column(name = "importe")
<<<<<<< HEAD
    private float importe;
    @Column(name = "fecha")
    private Date fecha;
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "transaccion_id")
    private List<FacturaEntity> facturas;
    @Column(name = "medio_pago_id")
    @Enumerated(EnumType.ORDINAL)
    private MedioPago medioPago;
=======
    private Float importe;
    @Column(name = "fecha")
    private Date fecha;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "medio_pago")
    private MedioPago medioPago;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaccion_id")
    private List<FacturaEntity> facturas;
>>>>>>> develop

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

<<<<<<< HEAD
    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
=======
    public Float getImporte() {
        return importe;
    }

    public void setImporte(Float importe) {
>>>>>>> develop
        this.importe = importe;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

<<<<<<< HEAD
    public List<FacturaEntity> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<FacturaEntity> facturas) {
        this.facturas = facturas;
    }

=======
>>>>>>> develop
    public MedioPago getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(MedioPago medioPago) {
        this.medioPago = medioPago;
    }
<<<<<<< HEAD
=======

    public List<FacturaEntity> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<FacturaEntity> facturas) {
        this.facturas = facturas;
    }
>>>>>>> develop
}
