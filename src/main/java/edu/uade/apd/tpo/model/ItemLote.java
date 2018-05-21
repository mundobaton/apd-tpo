package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.entity.ItemLoteEntity;

public class ItemLote {

    private Integer id;
    private Lote lote;
    private int cantidad;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public static ItemLote fromEntity(ItemLoteEntity entity) {
        ItemLote il = null;
        if (entity != null) {
            il = new ItemLote();
            il.setId(entity.getId());
            il.setCantidad(entity.getCantidad());
            il.setLote(Lote.fromEntity(entity.getLote()));
        }
        return il;
    }

    public ItemLoteEntity toEntity() {
        ItemLoteEntity entity = new ItemLoteEntity();
        entity.setId(id);
        entity.setCantidad(cantidad);
        entity.setLote(lote != null ? lote.toEntity() : null);
        return entity;
    }
}
