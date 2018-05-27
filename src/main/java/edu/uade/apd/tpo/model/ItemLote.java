package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.ItemLoteDao;
import edu.uade.apd.tpo.entity.ItemLoteEntity;

public class ItemLote {
    private Long id;
    private Lote lote;
    private int cantidad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public void guardar() {
        ItemLoteDao.getInstance().save(this.toEntity());
    }

    public static ItemLote fromEntity(ItemLoteEntity entity) {
        ItemLote il = null;
        if (entity != null) {
            il = new ItemLote();
            il.setId(entity.getId());
            il.setLote(Lote.fromEntity(entity.getLote(), Articulo.fromEntity(entity.getLote().getArticulo())));
            il.setCantidad(entity.getCantidad());
        }
        return il;
    }

    public ItemLoteEntity toEntity() {
        ItemLoteEntity ile = new ItemLoteEntity();
        ile.setId(id);
        ile.setLote(lote != null ? lote.toEntity(lote.getArticulo().toEntity()) : null);
        ile.setCantidad(cantidad);
        return ile;
    }

}
