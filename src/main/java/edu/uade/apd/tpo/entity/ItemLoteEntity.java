package edu.uade.apd.tpo.entity;

import edu.uade.apd.tpo.model.ItemPedido;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "item_lotes")
public class ItemLoteEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_lote_id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "lote_id")
    private LoteEntity lote;
    @Column(name = "cantidad")
    private int cantidad;
    @ManyToOne
    @JoinColumn(name = "item_pedido_id")
    private ItemPedidoEntity itemPedido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LoteEntity getLote() {
        return lote;
    }

    public void setLote(LoteEntity lote) {
        this.lote = lote;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public ItemPedidoEntity getItemPedido() {
        return itemPedido;
    }

    public void setItemPedido(ItemPedidoEntity itemPedido) {
        this.itemPedido = itemPedido;
    }
}
