package edu.uade.apd.tpo.model;

<<<<<<< HEAD
import edu.uade.apd.tpo.dao.TransaccionDao;
import edu.uade.apd.tpo.entity.FacturaEntity;
import edu.uade.apd.tpo.entity.TransaccionEntity;
import edu.uade.apd.tpo.repository.stub.FacturaStub;
import edu.uade.apd.tpo.repository.stub.TransaccionStub;

import java.util.ArrayList;
=======
>>>>>>> develop
import java.util.Date;
import java.util.List;

public class Transaccion {

    private Long id;
    private float importe;
    private Date fecha;
<<<<<<< HEAD
    private List<Factura> facturas;
    private MedioPago medioPago;

    public Long getId() {
=======
    private MedioPago medioPago;
    private List<Factura> facturas;

	public Long getId() {
>>>>>>> develop
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

<<<<<<< HEAD
    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
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
    public void agregarFactura(Factura factura) {
        this.facturas.add(factura);
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
                    t.getFacturas().add(Factura.fromEntity(fe));
                }
            }
            t.setMedioPago(entity.getMedioPago());
        }
        return t;
    }

    public static Transaccion fromStub(TransaccionStub stub) {
        Transaccion t = null;
        if (stub != null) {
            t = new Transaccion();
            t.setId(stub.getId());
            t.setImporte(stub.getImporte());
            t.setFecha(stub.getFecha());
            if (stub.getFacturas() != null) {
                t.setFacturas(new ArrayList<>());
                for (FacturaStub fe : stub.getFacturas()) {
                    t.getFacturas().add(Factura.fromStub(fe));
                }
            }
            t.setMedioPago(MedioPago.fromStub(stub.getMedioPago()));
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
                entity.getFacturas().add(f.toEntity());
            }
        }
        entity.setMedioPago(medioPago);
        return entity;
    }

    public TransaccionStub toStub() {
        TransaccionStub stub = new TransaccionStub();
        stub.setId(id);
        stub.setImporte(importe);
        stub.setFecha(fecha);

        if (facturas != null) {
            stub.setFacturas(new ArrayList<>());
            for (Factura f : facturas) {
                stub.getFacturas().add(f.toStub());
            }
        }
        stub.setMedioPago(medioPago.toStub());
        return stub;
=======
    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }
    
    public void addFactura(Factura factura){
    		this.facturas.add(factura);
>>>>>>> develop
    }
}
