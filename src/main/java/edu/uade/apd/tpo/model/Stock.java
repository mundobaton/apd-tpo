package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.entity.EgresoEntity;
import edu.uade.apd.tpo.entity.IngresoEntity;
import edu.uade.apd.tpo.entity.MovimientoEntity;
import edu.uade.apd.tpo.entity.StockEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Stock {

    private Integer id;
    private List<Movimiento> movimientos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public void agregarMovimientoIngreso(MotivoIngreso motivo, int cant) {
        if (movimientos == null) {
            movimientos = new ArrayList<>();
        }
        Ingreso ingreso = new Ingreso();
        ingreso.setMotivoIngreso(motivo);
        ingreso.setCantidad(cant);
        ingreso.setFecha(new Date());
        movimientos.add(ingreso);
    }

    public static Stock fromEntity(StockEntity entity) {
        Stock stock = null;
        if (entity != null) {
            stock = new Stock();
            stock.setId(entity.getId());
            if (entity.getMovimientos() != null) {
                List<Movimiento> movimientos = new ArrayList<>();
                for (MovimientoEntity me : entity.getMovimientos()) {
                    if (me instanceof EgresoEntity) {
                        movimientos.add(Egreso.fromEntity((EgresoEntity) me));
                    } else {
                        movimientos.add(Ingreso.fromEntity((IngresoEntity) me));
                    }
                    stock.setMovimientos(movimientos);
                }
            }
        }


        return stock;
    }

    public StockEntity toEntity() {
        StockEntity se = new StockEntity();
        se.setId(id);
        if (getMovimientos() != null) {
            List<MovimientoEntity> movimientos = new ArrayList<>();
            for (Movimiento m : getMovimientos()) {
                if (m instanceof Ingreso) {
                    movimientos.add(((Ingreso) m).toEntity());
                } else {
                    movimientos.add(((Egreso) m).toEntity());
                }
                se.setMovimientos(movimientos);
            }
        }
        return se;
    }

}
