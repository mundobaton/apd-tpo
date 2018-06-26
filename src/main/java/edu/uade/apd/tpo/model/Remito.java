package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.RemitoDao;

import java.util.List;

public class Remito {

    private Long id;
    private List<ItemPedido> items;
    private Domicilio domicilio;

    public Remito(List<ItemPedido> items, Domicilio domicilio) {
        this.items = items;
        this.domicilio = domicilio;
    }

    public Remito() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItemPedido> getItems() {
        return items;
    }

    public void setItems(List<ItemPedido> items) {
        this.items = items;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public void guardar() {
        RemitoDao.getInstance().save(this);
    }
}
