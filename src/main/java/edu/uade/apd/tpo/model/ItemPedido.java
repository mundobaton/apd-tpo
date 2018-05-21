package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.entity.ItemLoteEntity;
import edu.uade.apd.tpo.entity.ItemPedidoEntity;

import java.util.ArrayList;
import java.util.List;

public class ItemPedido {

    private Integer id;
    private Articulo articulo;
    private int cantidad;
    private List<ItemLote> lotes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<ItemLote> getLotes() {
        return lotes;
    }

    public void setLotes(List<ItemLote> lotes) {
        this.lotes = lotes;
    }

    public Float getSubtotal() {
        return this.articulo.getPrecio() * this.cantidad;
    }

    /**
     * Es completo el item si la suma de las cantidades de todos los lotes es igual
     * a la cantidad pedida
     *
     * @return
     */
    public boolean esCompleto() {
        return cantidad == lotes.stream().mapToInt(l -> l.getCantidad()).sum();
    }

    public static ItemPedido fromEntity(ItemPedidoEntity entity) {
        ItemPedido ip = null;
        if (entity != null) {
            ip = new ItemPedido();
            ip.setId(entity.getId());
            ip.setArticulo(Articulo.fromEntity(entity.getArticulo()));
            ip.setCantidad(entity.getCantidad());
            if (entity.getLotes() != null) {
                List<ItemLote> lotes = new ArrayList<>();
                for (ItemLoteEntity ile : entity.getLotes()) {
                    lotes.add(ItemLote.fromEntity(ile));
                }
                ip.setLotes(lotes);
            }

        }
        return ip;
    }

    public ItemPedidoEntity toEntity() {
        ItemPedidoEntity ip = new ItemPedidoEntity();
        ip.setId(id);
        ip.setArticulo(articulo != null ? articulo.toEntity() : null);
        ip.setCantidad(cantidad);
        if (this.getLotes() != null) {
            List<ItemLoteEntity> lotes = new ArrayList<>();
            for(ItemLote il : this.getLotes()) {
                lotes.add(il.toEntity());
            }
            ip.setLotes(lotes);
        }
        return ip;
    }
}
