package edu.uade.apd.tpo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "remitos")
public class RemitoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "remito_id")
    private Long numeroRemito;
    @OneToMany
    @JoinColumn(name = "remito_id")
    private List<ItemPedidoEntity> items;

    public long getNumeroRemito() {
        return numeroRemito;
    }

    public void setNumeroRemito(long numeroRemito) {
        this.numeroRemito = numeroRemito;
    }

    public List<ItemPedidoEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemPedidoEntity> items) {
        this.items = items;
    }
}
