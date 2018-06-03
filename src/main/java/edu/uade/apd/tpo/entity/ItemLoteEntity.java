package edu.uade.apd.tpo.entity;

<<<<<<< HEAD
import edu.uade.apd.tpo.model.ItemPedido;

=======
>>>>>>> develop
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
<<<<<<< HEAD
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "item_lotes")
public class ItemLoteEntity implements Serializable {
=======
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item_lotes")
public class ItemLoteEntity extends BaseEntity {

>>>>>>> develop
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_lote_id")
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lote_id")
    private LoteEntity lote;
    @Column(name = "cantidad")
    private int cantidad;

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
<<<<<<< HEAD

=======
>>>>>>> develop
}
