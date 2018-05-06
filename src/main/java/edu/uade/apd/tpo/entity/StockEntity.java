package edu.uade.apd.tpo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "stocks")
public class StockEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long id;
    @OneToMany
    @JoinColumn(name = "stock_id")
    private List<MovimientoEntity> movimientos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<MovimientoEntity> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoEntity> movimientos) {
        this.movimientos = movimientos;
    }
}
