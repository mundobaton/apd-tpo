package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.entity.FacturaEntity;
import edu.uade.apd.tpo.entity.TransaccionEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Transaccion {

    private Integer id;
    private Float importe;
    private Date fecha;
    private MedioPago medioPago;
    private List<Factura> facturas;

    public TransaccionEntity toEntity() {
        TransaccionEntity te = new TransaccionEntity();
        te.setId(id);
        te.setImporte(importe);
        te.setFecha(fecha);
        te.setMedioPago(medioPago);
        if (getFacturas() != null) {
            List<FacturaEntity> facturas = new ArrayList<>();
            for (Factura f : getFacturas()) {
                facturas.add(f.toEntity());
            }
            te.setFacturas(facturas);
        }

        return te;
    }

    public static Transaccion fromEntity(TransaccionEntity entity) {
        Transaccion t = null;
        if (entity != null) {
            t = new Transaccion();
            t.setId(entity.getId());
            t.setImporte(entity.getImporte());
            t.setFecha(entity.getFecha());
            t.setMedioPago(entity.getMedioPago());
            if(entity.getFacturas() != null) {
                List<Factura> facturas = new ArrayList<>();
                for(FacturaEntity fe : entity.getFacturas()) {
                    facturas.add(Factura.fromEntity(fe));
                }
                t.setFacturas(facturas);
            }
        }

        return t;
    }

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

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }
}
