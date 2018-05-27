package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.StockDao;
import edu.uade.apd.tpo.entity.EgresoEntity;
import edu.uade.apd.tpo.entity.IngresoEntity;
import edu.uade.apd.tpo.entity.MovimientoEntity;
import edu.uade.apd.tpo.entity.StockEntity;

import java.util.ArrayList;
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
        return 0;
    }

    public void agregarMovimiento(Movimiento mov) {
        movimientos.add(mov);
    }

    public void agregarMovimientoIngreso(MotivoIngreso m, int cantidad) {

    }

    public void agregarMovimientoEgreso(MotivoEgreso motivo, int cantidad) {
        Egreso egreso = new Egreso();
        egreso.setCantidad(cantidad);
        egreso.setMotivo(motivo);
        agregarMovimiento(egreso);
    }

    public void reservar(int cantidad) {

    }

    public void liberarReserva(int cantidad) {

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

    public void guardar() {
        StockDao.getInstance().save(this.toEntity());
    }

}
