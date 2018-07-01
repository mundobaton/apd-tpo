package edu.uade.apd.tpo.entity;

import edu.uade.apd.tpo.model.ItemPedido;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "orden_compras")
public class OrdenCompraEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orden_compra_id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "item_pedido_id")
    private ItemPedidoEntity item;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedido;
    @Column(name = "estado")
    private char estado;
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ItemPedidoEntity getItem() {
        return item;
    }

    public void setItem(ItemPedidoEntity item) {
        this.item = item;
    }

    public PedidoEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
