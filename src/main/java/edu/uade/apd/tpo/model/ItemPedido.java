package edu.uade.apd.tpo.model;

import java.util.ArrayList;
import java.util.List;

public class ItemPedido {

    private Long id;
    private Articulo articulo;
    private int cantidad;
    private List<ItemLote> items;

    public ItemPedido(Articulo articulo, int cantidad) {
        this.articulo = articulo;
        this.cantidad = cantidad;
    }

    public ItemPedido() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<ItemLote> getItems() {
        return items;
    }

    public void setItems(List<ItemLote> items) {
        this.items = items;
    }

    public float getPrecio() {
        return articulo.getPrecio() * cantidad;
    }

    public void agregarLote(ItemLote itemLote) {
        if (this.items == null) {
            items = new ArrayList<>();
        }
        items.add(itemLote);
    }
}
