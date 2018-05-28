package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.StockDao;
import edu.uade.apd.tpo.entity.EgresoEntity;
import edu.uade.apd.tpo.entity.IngresoEntity;
import edu.uade.apd.tpo.entity.MovimientoEntity;
import edu.uade.apd.tpo.entity.StockEntity;
import edu.uade.apd.tpo.repository.stub.EgresoStub;
import edu.uade.apd.tpo.repository.stub.IngresoStub;
import edu.uade.apd.tpo.repository.stub.MovimientoStub;
import edu.uade.apd.tpo.repository.stub.StockStub;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Stock {
    private Long id;
    private List<Movimiento> movimientos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public int calcular() {
        int stock = 0;
        for (Movimiento m : movimientos) {
            if (m instanceof Ingreso) {
                stock += m.getCantidad();
            } else {
                stock -= m.getCantidad();
            }
        }
        return stock;
    }

    public void agregarMovimientoIngreso(MotivoIngreso m, int cantidad) {
        Ingreso ingreso = new Ingreso();
        ingreso.setFecha(new Date());
        ingreso.setCantidad(cantidad);
        ingreso.setMotivo(m);
        movimientos.add(ingreso);
    }

    public void agregarMovimientoEgreso(MotivoEgreso motivo, int cantidad) {
        Egreso egreso = new Egreso();
        egreso.setFecha(new Date());
        egreso.setCantidad(cantidad);
        egreso.setMotivo(motivo);
        movimientos.add(egreso);
    }

    public void agregarMovimientoEgreso(MotivoEgreso motivo, int cantidad, Usuario encargado, String autorizante, String destino) {
        Egreso egreso = new Egreso();
        egreso.setFecha(new Date());
        egreso.setCantidad(cantidad);
        egreso.setMotivo(motivo);
        egreso.setEncargado(encargado);
        egreso.setAutorizante(autorizante);
        egreso.setDestino(destino);
        movimientos.add(egreso);
    }

    public void reservar(int cantidad) {
        this.agregarMovimientoEgreso(MotivoEgreso.RESERVA, cantidad);
    }

    public void liberarReserva(int cantidad) {
        this.agregarMovimientoIngreso(MotivoIngreso.LIBERA_RESERVA, cantidad);
    }

    public static Stock fromEntity(StockEntity entity) {
        Stock s = null;
        if (entity != null) {
            s = new Stock();
            s.setId(entity.getId());
            if (entity.getMovimientos() != null) {
                s.setMovimientos(new ArrayList<>());
                for (MovimientoEntity m : entity.getMovimientos()) {
                    if (m instanceof IngresoEntity) {
                        s.getMovimientos().add(Ingreso.fromEntity((IngresoEntity) m));
                    } else {
                        s.getMovimientos().add(Egreso.fromEntity((EgresoEntity) m));
                    }
                }
            }
        }
        return s;
    }

    public static Stock fromStub(StockStub stub) {
        Stock s = null;
        if (stub != null) {
            s = new Stock();
            s.setId(stub.getId());
            if (stub.getMovimientos() != null) {
                s.setMovimientos(new ArrayList<>());
                for (MovimientoStub m : stub.getMovimientos()) {
                    if (m instanceof IngresoStub) {
                        s.getMovimientos().add(Ingreso.fromStub((IngresoStub) m));
                    } else {
                        s.getMovimientos().add(Egreso.fromStub((EgresoStub) m));
                    }
                }
            }
        }
        return s;
    }

    public StockEntity toEntity() {
        StockEntity entity = new StockEntity();
        entity.setId(id);
        if (movimientos != null) {
            entity.setMovimientos(new ArrayList<>());
            for (Movimiento m : movimientos) {
                if (m instanceof Ingreso) {
                    entity.getMovimientos().add(((Ingreso) m).toEntity());
                } else {
                    entity.getMovimientos().add(((Egreso) m).toEntity());
                }
            }
        }
        return entity;
    }

    public StockStub toStub() {
        StockStub stub = new StockStub();
        stub.setId(id);
        if (movimientos != null) {
            stub.setMovimientos(new ArrayList<>());
            for (Movimiento m : movimientos) {
                if (m instanceof Ingreso) {
                    stub.getMovimientos().add(((Ingreso) m).toStub());
                } else {
                    stub.getMovimientos().add(((Egreso) m).toStub());
                }
            }
        }
        return stub;
    }

    public void guardar() {
        StockDao.getInstance().save(this.toEntity());
    }

}
