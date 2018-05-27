package edu.uade.apd.tpo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "item_pedidos")
public class ItemPedidoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_pedido_id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "articulo_id")
    private ArticuloEntity articulo;
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "subtotal")
    private float subTotal;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_pedido_id")
    private List<ItemLoteEntity> lotes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArticuloEntity getArticulo() {
        return articulo;
    }

    public void setArticulo(ArticuloEntity articulo) {
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

    public List<ItemLoteEntity> getLotes() {
        return lotes;
    }

    public void setLotes(List<ItemLoteEntity> lotes) {
        this.lotes = lotes;
    }
}
