package edu.uade.apd.tpo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import edu.uade.apd.tpo.model.Factura;
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

@Entity
@Table(name = "transacciones")
public class TransaccionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaccion_id")
    private Long id;
    @Column(name = "importe")
    private float importe;
    @Column(name = "fecha")
    private Date fecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transaccion")
    private List<FacturaEntity> facturas;
    @Column(name = "medio_pago_id")
    @Enumerated(EnumType.ORDINAL)
    private MedioPago medioPago;
    @ManyToOne
    @JoinColumn(name = "cuenta_corriente_id")
    private CuentaCorrienteEntity cuentaCorriente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<FacturaEntity> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<FacturaEntity> facturas) {
        this.facturas = facturas;
    }

    public MedioPago getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(MedioPago medioPago) {
        this.medioPago = medioPago;
    }

    public CuentaCorrienteEntity getCuentaCorriente() {
        return cuentaCorriente;
    }

    public void setCuentaCorriente(CuentaCorrienteEntity cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
    }
}
