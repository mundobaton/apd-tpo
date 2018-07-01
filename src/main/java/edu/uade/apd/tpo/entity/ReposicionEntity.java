package edu.uade.apd.tpo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "reposiciones")
public class ReposicionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reposicion_id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "item_pedido_id")
    private ItemPedidoEntity itemPedido;
    @OneToOne
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedido;
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "estado")
    private char estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ItemPedidoEntity getItemPedido() {
        return itemPedido;
    }

    public void setItemPedido(ItemPedidoEntity itemPedido) {
        this.itemPedido = itemPedido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public PedidoEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }
}
