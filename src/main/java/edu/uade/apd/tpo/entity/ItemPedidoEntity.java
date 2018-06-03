package edu.uade.apd.tpo.entity;

<<<<<<< HEAD
=======
import edu.uade.apd.tpo.model.Pedido;

>>>>>>> develop
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
<<<<<<< HEAD
import javax.persistence.Transient;
import java.io.Serializable;
=======
>>>>>>> develop
import java.util.List;

@Entity
@Table(name = "item_pedidos")
<<<<<<< HEAD
public class ItemPedidoEntity implements Serializable {
=======
public class ItemPedidoEntity extends BaseEntity {
>>>>>>> develop

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_pedido_id")
    private Long id;
<<<<<<< HEAD
    @OneToOne
=======
    @OneToOne(cascade = CascadeType.ALL)
>>>>>>> develop
    @JoinColumn(name = "articulo_id")
    private ArticuloEntity articulo;
    @Column(name = "cantidad")
    private int cantidad;
<<<<<<< HEAD
    @Column(name = "subtotal")
    private float subTotal;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_pedido_id")
    private List<ItemLoteEntity> lotes;
=======
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_pedido_id")
    private List<ItemLoteEntity> lotes;
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedido;
>>>>>>> develop

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

<<<<<<< HEAD
    public ArticuloEntity getArticulo() {
        return articulo;
    }

    public void setArticulo(ArticuloEntity articulo) {
        this.articulo = articulo;
    }

=======
>>>>>>> develop
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

<<<<<<< HEAD
    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

=======
>>>>>>> develop
    public List<ItemLoteEntity> getLotes() {
        return lotes;
    }

    public void setLotes(List<ItemLoteEntity> lotes) {
        this.lotes = lotes;
    }
<<<<<<< HEAD
=======

    public ArticuloEntity getArticulo() {
        return articulo;
    }

    public void setArticulo(ArticuloEntity articulo) {
        this.articulo = articulo;
    }

    public PedidoEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }
>>>>>>> develop
}
