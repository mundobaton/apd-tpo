package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.ItemPosicionDao;
import edu.uade.apd.tpo.entity.ItemPosicionEntity;

public class ItemPosicion {

    private Long id;
    private int cantidad;
    private Posicion posicion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public static ItemPosicion fromEntity(ItemPosicionEntity ipe) {
        ItemPosicion ip = null;
        if (ipe != null) {
            ip = new ItemPosicion();
            ip.setId(ipe.getId());
            ip.setCantidad(ipe.getCantidad());
            ip.setPosicion(Posicion.fromEntity(ipe.getPosicion()));
        }
        return ip;
    }

    public ItemPosicionEntity toEntity() {
        ItemPosicionEntity ipe = new ItemPosicionEntity();
        ipe.setId(id);
        ipe.setCantidad(cantidad);
        ipe.setPosicion(posicion != null ? posicion.toEntity() : null);
        return ipe;
    }

    public void guardar() {
        ItemPosicionDao.getInstance().save(this.toEntity());
    }

}
