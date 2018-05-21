package edu.uade.apd.tpo.entity;

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
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "transacciones")
public class TransaccionEntity implements Persistible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaccion_id")
    private Integer id;
    @Column(name = "importe")
    private Float importe;
    @Column(name = "fecha")
    private Date fecha;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "medio_pago")
    private MedioPago medioPago;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transaccion")
    private List<FacturaEntity> facturas;
    @ManyToOne
    @JoinColumn(name = "cuenta_corriente_id")
    private CuentaCorrienteEntity cuentaCorriente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getImporte() {
        return importe;
    }

    public void setImporte(Float importe) {
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

    public List<FacturaEntity> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<FacturaEntity> facturas) {
        this.facturas = facturas;
    }

    public CuentaCorrienteEntity getCuentaCorriente() {
        return cuentaCorriente;
    }

    public void setCuentaCorriente(CuentaCorrienteEntity cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
    }
}
