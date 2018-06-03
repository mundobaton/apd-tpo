package edu.uade.apd.tpo.entity;

<<<<<<< HEAD
=======
import edu.uade.apd.tpo.model.Factura;

import javax.persistence.CascadeType;
>>>>>>> develop
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD
import javax.persistence.Table;
import java.io.Serializable;
=======
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
>>>>>>> develop
import java.util.Date;

@Entity
@Table(name = "remitos")
<<<<<<< HEAD
public class RemitoEntity implements Serializable {
=======
public class RemitoEntity extends BaseEntity {
>>>>>>> develop

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "remito_id")
    private Long id;
    @Column(name = "fecha")
    private Date fecha;
<<<<<<< HEAD
=======
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "factura_id")
    private FacturaEntity factura;
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

=======
    public FacturaEntity getFactura() {
        return factura;
    }

    public void setFactura(FacturaEntity factura) {
        this.factura = factura;
    }
>>>>>>> develop
}
