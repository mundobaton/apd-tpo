package edu.uade.apd.tpo.entity;

import edu.uade.apd.tpo.model.Movimiento;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "stocks")
public class StockEntity extends BaseEntity {

    private Long id;
    private int cantidad;
    private List<Movimiento> movimientos;

}
