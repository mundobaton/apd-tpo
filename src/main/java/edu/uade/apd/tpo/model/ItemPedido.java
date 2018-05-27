package edu.uade.apd.tpo.model;

import java.util.ArrayList;
import java.util.List;

import edu.uade.apd.tpo.dao.ItemPedidoDao;
import edu.uade.apd.tpo.entity.ItemLoteEntity;
import edu.uade.apd.tpo.entity.ItemPedidoEntity;

public class ItemPedido {

    private Long id;
    private Articulo articulo;
    private int cantidad;
    private float subTotal;
    private List<ItemLote> lotes;

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

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public List<ItemLote> getLotes() {
        return lotes;
    }

    public void setLotes(List<ItemLote> lotes) {
        this.lotes = lotes;
    }

    //TODO revisar comportamiento de la funcion
    public void actualizar(int cantidad) {
        this.cantidad += cantidad;
    }

    //TODO revisar comportamiento de la funcion
    public void agregarLote(Lote lote, int cantidad) {
        int index = 0;
        boolean salir = true;
        if (this.lotes.get(index) == null) {
            ItemLote itemLote = new ItemLote();
            itemLote.setLote(lote);
            itemLote.setCantidad(cantidad);
            salir = false;
        }
        while (this.lotes.get(index) != null && salir == true) {
            if (this.lotes.get(index).getLote().getId() == lote.getId()) {
                int cantAnterior = this.lotes.get(index).getCantidad();
                this.lotes.get(index).setCantidad(cantidad + cantAnterior);
                salir = false;
            }
        }
    }

    public static ItemPedido fromEntity(ItemPedidoEntity entity) {
        ItemPedido ip = null;
        if (entity != null) {
            ip = new ItemPedido();
            ip.setId(entity.getId());
            ip.setArticulo(ip.getArticulo().fromEntity(entity.getArticulo()));
            ip.setCantidad(entity.getCantidad());
            ip.setSubTotal(entity.getSubTotal());
            if (entity.getLotes() != null) {
                ip.setLotes(new ArrayList<>());
                for (ItemLoteEntity ile : entity.getLotes()) {
                    ip.getLotes().add(ItemLote.fromEntity(ile));
                }
            }
        }

        return ip;
    }

    public ItemPedidoEntity toEntity() {
        ItemPedidoEntity entity = new ItemPedidoEntity();
        entity.setId(id);
        entity.setArticulo(articulo.toEntity());
        entity.setCantidad(cantidad);
        entity.setSubTotal(subTotal);
        if (lotes != null) {
            entity.setLotes(new ArrayList<>());
            for (ItemLote il : lotes) {
                entity.getLotes().add(il.toEntity());
            }
        }
        return entity;
    }

    public void guardar() {
        ItemPedidoDao.getInstance().save(this.toEntity());
    }

}
