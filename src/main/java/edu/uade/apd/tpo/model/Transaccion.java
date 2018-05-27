package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.TransaccionDao;
import edu.uade.apd.tpo.entity.FacturaEntity;
import edu.uade.apd.tpo.entity.TransaccionEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Transaccion {

    private Long id;
    private float importe;
    private Date fecha;
    private List<Factura> facturas;
    private MedioPago medioPago;

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

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public MedioPago getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(MedioPago medioPago) {
        this.medioPago = medioPago;
    }

    public void agregarFactura(Factura fatura) {
        this.facturas.add(fatura);
    }

    public void guardar() {
        TransaccionDao.getInstance().save(this.toEntity());
    }

    public static Transaccion fromEntity(TransaccionEntity entity) {
        Transaccion t = null;
        if (entity != null) {
            t = new Transaccion();
            t.setId(entity.getId());
            t.setImporte(entity.getImporte());
            t.setFecha(entity.getFecha());
            if (entity.getFacturas() != null) {
                t.setFacturas(new ArrayList<>());
                for (FacturaEntity fe : entity.getFacturas()) {
                    t.getFacturas().add(Factura.fromEntity(fe, t));
                }
            }
            t.setMedioPago(entity.getMedioPago());
        }
        return t;
    }

    public TransaccionEntity toEntity() {
        TransaccionEntity entity = new TransaccionEntity();
        entity.setId(id);
        entity.setImporte(importe);
        entity.setFecha(fecha);

        if (facturas != null) {
            entity.setFacturas(new ArrayList<>());
            for (Factura f : facturas) {
                entity.getFacturas().add(f.toEntity(entity));
            }
        }
        entity.setMedioPago(medioPago);
        return entity;
    }
}
