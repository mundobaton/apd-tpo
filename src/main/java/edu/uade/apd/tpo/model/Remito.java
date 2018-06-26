package edu.uade.apd.tpo.model;

import java.util.List;

public class Remito {

    private Long id;
    private long numeroRemito;
    private List<ItemPedido> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getNumeroRemito() {
        return numeroRemito;
    }

    public void setNumeroRemito(long numeroRemito) {
        this.numeroRemito = numeroRemito;
    }

    public List<ItemPedido> getItems() {
        return items;
    }

    public void setItems(List<ItemPedido> items) {
        this.items = items;
    }
}
